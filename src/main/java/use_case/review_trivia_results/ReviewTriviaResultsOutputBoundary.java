package use_case.review_trivia_results;

public interface ReviewTriviaResultsOutputBoundary {
    void prepareSuccessView(ReviewTriviaResultsOutputData output);

    void prepareFailView(String message);
}
