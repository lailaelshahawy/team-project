package use_case.review_trivia_results;

/**
 * Reviewing results needs no input from the player: it always reviews the current quiz,
 * so there is no Input Data class for this use case.
 */
public interface ReviewTriviaResultsInputBoundary {
    void execute();
}
