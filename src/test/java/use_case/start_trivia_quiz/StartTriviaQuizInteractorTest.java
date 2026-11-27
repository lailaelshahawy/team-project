package use_case.start_trivia_quiz;

import entity.TriviaQuestion;
import entity.TriviaSession;
import org.junit.Test;
import use_case.TriviaTestData;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class StartTriviaQuizInteractorTest {

    /** Fake session store: just remembers what was saved. */
    private static class FakeSessionStore implements StartTriviaQuizSessionDataAccessInterface {
        TriviaSession saved;

        @Override
        public void saveSession(TriviaSession session) {
            saved = session;
        }
    }

    /** Fake presenter: records what the interactor sent it. */
    private static class RecordingPresenter implements StartTriviaQuizOutputBoundary {
        StartTriviaQuizOutputData success;
        String failure;

        @Override
        public void prepareSuccessView(StartTriviaQuizOutputData output) {
            success = output;
        }

        @Override
        public void prepareFailView(String message) {
            failure = message;
        }
    }

    @Test
    public void startsQuizAndShowsFirstQuestion() {
        List<TriviaQuestion> questions = TriviaTestData.questions(10);
        String[] requested = new String[2];
        StartTriviaQuizDataAccessInterface questionSource = (category, difficulty) -> {
            requested[0] = category;
            requested[1] = difficulty;
            return questions;
        };
        FakeSessionStore store = new FakeSessionStore();
        RecordingPresenter presenter = new RecordingPresenter();

        new StartTriviaQuizInteractor(questionSource, store, presenter)
                .execute(new StartTriviaQuizInputData("history", "hard"));

        assertEquals("history", requested[0]);
        assertEquals("hard", requested[1]);
        assertNotNull(store.saved);
        assertNull(presenter.failure);
        assertEquals(1, presenter.success.getQuestionNumber());
        assertEquals(10, presenter.success.getTotalQuestions());
        assertEquals("Q1", presenter.success.getQuestionText());
        assertEquals(questions.get(0).getOptions(), presenter.success.getOptions());
    }

    @Test
    public void failsWhenApiReturnsTooFewQuestions() {
        FakeSessionStore store = new FakeSessionStore();
        RecordingPresenter presenter = new RecordingPresenter();

        new StartTriviaQuizInteractor((c, d) -> TriviaTestData.questions(9), store, presenter)
                .execute(new StartTriviaQuizInputData("history", "hard"));

        assertEquals("A quiz needs ten questions. Please try again.", presenter.failure);
        assertNull(presenter.success);
        assertNull(store.saved);
    }

    @Test
    public void failsWhenApiCallFails() {
        StartTriviaQuizDataAccessInterface brokenSource = (c, d) -> {
            throw new IllegalStateException("Could not load trivia questions. Please try again.");
        };
        FakeSessionStore store = new FakeSessionStore();
        RecordingPresenter presenter = new RecordingPresenter();

        new StartTriviaQuizInteractor(brokenSource, store, presenter)
                .execute(new StartTriviaQuizInputData("science", "easy"));

        assertEquals("Could not load trivia questions. Please try again.", presenter.failure);
        assertNull(store.saved);
    }
}
