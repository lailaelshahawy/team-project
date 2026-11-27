package use_case.review_trivia_results;

import entity.TriviaSession;
import org.junit.Test;
import use_case.TriviaTestData;
import use_case.review_trivia_results.ReviewTriviaResultsOutputData.QuestionReview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ReviewTriviaResultsInteractorTest {

    private static class RecordingPresenter implements ReviewTriviaResultsOutputBoundary {
        ReviewTriviaResultsOutputData success;
        String failure;

        @Override
        public void prepareSuccessView(ReviewTriviaResultsOutputData output) {
            success = output;
        }

        @Override
        public void prepareFailView(String message) {
            failure = message;
        }
    }

    @Test
    public void reviewsEveryQuestionInOrder() {
        List<String> answers = new ArrayList<>(Collections.nCopies(10, "Right"));
        answers.set(2, "Wrong1");
        TriviaSession session = TriviaTestData.finishedSession(answers);
        RecordingPresenter presenter = new RecordingPresenter();

        new ReviewTriviaResultsInteractor(() -> session, presenter).execute();

        ReviewTriviaResultsOutputData out = presenter.success;
        assertNull(presenter.failure);
        assertEquals(9, out.getScore());
        assertEquals(10, out.getTotalQuestions());
        assertEquals(10, out.getReviews().size());

        QuestionReview first = out.getReviews().get(0);
        assertEquals("Q1", first.getQuestionText());
        assertEquals("Right", first.getSelectedAnswer());
        assertTrue(first.isCorrect());

        QuestionReview third = out.getReviews().get(2);
        assertEquals("Q3", third.getQuestionText());
        assertEquals("Wrong1", third.getSelectedAnswer());
        assertEquals("Right", third.getCorrectAnswer());
        assertFalse(third.isCorrect());
    }

    @Test
    public void failsWhenNoQuizStarted() {
        RecordingPresenter presenter = new RecordingPresenter();

        new ReviewTriviaResultsInteractor(() -> null, presenter).execute();

        assertEquals("There is no quiz to review. Start a quiz first.", presenter.failure);
        assertNull(presenter.success);
    }

    @Test
    public void failsWhenQuizNotFinished() {
        TriviaSession session = new TriviaSession(TriviaTestData.questions(10));
        session.submitAnswer("Right");
        RecordingPresenter presenter = new RecordingPresenter();

        new ReviewTriviaResultsInteractor(() -> session, presenter).execute();

        assertEquals("Finish all ten questions before reviewing your results.", presenter.failure);
        assertNull(presenter.success);
    }
}
