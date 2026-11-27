package data_access;

import entity.TriviaSession;
import use_case.review_trivia_results.ReviewTriviaResultsDataAccessInterface;
import use_case.start_trivia_quiz.StartTriviaQuizSessionDataAccessInterface;
import use_case.submit_trivia_answer.SubmitTriviaAnswerDataAccessInterface;

/**
 * Keeps the current trivia quiz in memory while the app is running.
 * One instance is shared by the Start, Submit Answer, and Review Results use cases,
 * but each use case only sees the methods its own interface declares.
 */
public class InMemoryTriviaSessionDataAccessObject implements
        StartTriviaQuizSessionDataAccessInterface,
        SubmitTriviaAnswerDataAccessInterface,
        ReviewTriviaResultsDataAccessInterface {

    private TriviaSession session;

    @Override
    public void saveSession(TriviaSession session) {
        this.session = session;
    }

    /**
     * Returns the current quiz, or null if no quiz has been started.
     */
    @Override
    public TriviaSession getSession() {
        return session;
    }
}
