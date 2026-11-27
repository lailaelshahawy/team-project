package use_case.review_trivia_results;

import entity.TriviaSession;

public interface ReviewTriviaResultsDataAccessInterface {
    /**
     * Returns the most recent quiz, or null if no quiz has been started.
     */
    TriviaSession getSession();
}
