package interface_adapter.review_trivia_results;

import interface_adapter.trivia.TriviaState;
import interface_adapter.trivia.TriviaViewModel;
import use_case.review_trivia_results.ReviewTriviaResultsOutputBoundary;
import use_case.review_trivia_results.ReviewTriviaResultsOutputData;
import use_case.review_trivia_results.ReviewTriviaResultsOutputData.QuestionReview;

public class ReviewTriviaResultsPresenter implements ReviewTriviaResultsOutputBoundary {
    private final TriviaViewModel viewModel;

    public ReviewTriviaResultsPresenter(TriviaViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(ReviewTriviaResultsOutputData output) {
        StringBuilder text = new StringBuilder();
        text.append("Score: ").append(output.getScore())
                .append(" / ").append(output.getTotalQuestions()).append("\n\n");
        int number = 1;
        for (QuestionReview review : output.getReviews()) {
            text.append(number).append(". ").append(review.getQuestionText()).append("\n");
            text.append("   Your answer: ").append(review.getSelectedAnswer())
                    .append(review.isCorrect() ? "  (correct)" : "  (incorrect)").append("\n");
            if (!review.isCorrect()) {
                text.append("   Correct answer: ").append(review.getCorrectAnswer()).append("\n");
            }
            text.append("\n");
            number++;
        }

        TriviaState state = viewModel.getState();
        state.setError("");
        state.setReviewText(text.toString());
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        TriviaState state = viewModel.getState();
        state.setError(message);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }
}
