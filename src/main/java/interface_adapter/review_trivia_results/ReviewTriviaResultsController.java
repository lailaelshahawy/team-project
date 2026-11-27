package interface_adapter.review_trivia_results;

import use_case.review_trivia_results.ReviewTriviaResultsInputBoundary;

public class ReviewTriviaResultsController {
    private final ReviewTriviaResultsInputBoundary interactor;

    public ReviewTriviaResultsController(ReviewTriviaResultsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void review() {
        interactor.execute();
    }
}
