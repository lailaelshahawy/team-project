package use_case.submit_trivia_answer;

import entity.TriviaSession;
import org.junit.Test;
import use_case.TriviaTestData;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SubmitTriviaAnswerInteractorTest {

    private static class FakeSessionStore implements SubmitTriviaAnswerDataAccessInterface {
        TriviaSession session;
        int saveCount;

        FakeSessionStore(TriviaSession session) {
            this.session = session;
        }

        @Override
        public TriviaSession getSession() {
            return session;
        }

        @Override
        public void saveSession(TriviaSession session) {
            this.session = session;
            saveCount++;
        }
    }

    private static class RecordingPresenter implements SubmitTriviaAnswerOutputBoundary {
        SubmitTriviaAnswerOutputData success;
        String failure;

        @Override
        public void prepareSuccessView(SubmitTriviaAnswerOutputData output) {
            success = output;
        }

        @Override
        public void prepareFailView(String message) {
            failure = message;
        }
    }

    private TriviaSession newSession() {
        return new TriviaSession(TriviaTestData.questions(10));
    }

    @Test
    public void correctAnswerScoresAndShowsNextQuestion() {
        FakeSessionStore store = new FakeSessionStore(newSession());
        RecordingPresenter presenter = new RecordingPresenter();

        new SubmitTriviaAnswerInteractor(store, presenter).execute(new SubmitTriviaAnswerInputData("Right"));

        SubmitTriviaAnswerOutputData out = presenter.success;
        assertNull(presenter.failure);
        assertTrue(out.isCorrect());
        assertEquals("Right", out.getCorrectAnswer());
        assertEquals(1, out.getScore());
        assertEquals(10, out.getTotalQuestions());
        assertFalse(out.isQuizComplete());
        assertEquals(2, out.getNextQuestionNumber());
        assertEquals("Q2", out.getNextQuestionText());
        assertEquals(List.of("Right", "Wrong1", "Wrong2", "Wrong3"), out.getNextOptions());
        assertEquals(1, store.saveCount);
    }

    @Test
    public void incorrectAnswerReportsCorrectAnswerWithoutScoring() {
        FakeSessionStore store = new FakeSessionStore(newSession());
        RecordingPresenter presenter = new RecordingPresenter();

        new SubmitTriviaAnswerInteractor(store, presenter).execute(new SubmitTriviaAnswerInputData("Wrong2"));

        assertFalse(presenter.success.isCorrect());
        assertEquals("Right", presenter.success.getCorrectAnswer());
        assertEquals(0, presenter.success.getScore());
    }

    @Test
    public void lastAnswerCompletesQuiz() {
        TriviaSession session = newSession();
        for (int i = 0; i < 9; i++) {
            session.submitAnswer("Right");
        }
        FakeSessionStore store = new FakeSessionStore(session);
        RecordingPresenter presenter = new RecordingPresenter();

        new SubmitTriviaAnswerInteractor(store, presenter).execute(new SubmitTriviaAnswerInputData("Right"));

        SubmitTriviaAnswerOutputData out = presenter.success;
        assertTrue(out.isQuizComplete());
        assertEquals(10, out.getScore());
        assertEquals("", out.getNextQuestionText());
        assertTrue(out.getNextOptions().isEmpty());
        assertSame(session, store.session);
    }

    @Test
    public void failsWhenNoQuizStarted() {
        FakeSessionStore store = new FakeSessionStore(null);
        RecordingPresenter presenter = new RecordingPresenter();

        new SubmitTriviaAnswerInteractor(store, presenter).execute(new SubmitTriviaAnswerInputData("Right"));

        assertEquals("There is no quiz in progress. Start a quiz first.", presenter.failure);
        assertNull(presenter.success);
    }

    @Test
    public void failsWhenNoAnswerSelected() {
        FakeSessionStore store = new FakeSessionStore(newSession());
        RecordingPresenter presenter = new RecordingPresenter();

        new SubmitTriviaAnswerInteractor(store, presenter).execute(new SubmitTriviaAnswerInputData(null));

        assertEquals("Please choose one of the answer options.", presenter.failure);
        assertEquals(0, store.saveCount);
        assertEquals(0, store.session.getAnsweredCount());
    }

    @Test
    public void failsWhenQuizAlreadyComplete() {
        TriviaSession done = TriviaTestData.finishedSession(Collections.nCopies(10, "Right"));
        FakeSessionStore store = new FakeSessionStore(done);
        RecordingPresenter presenter = new RecordingPresenter();

        new SubmitTriviaAnswerInteractor(store, presenter).execute(new SubmitTriviaAnswerInputData("Right"));

        assertEquals("The quiz is already complete.", presenter.failure);
        assertEquals(0, store.saveCount);
    }
}
