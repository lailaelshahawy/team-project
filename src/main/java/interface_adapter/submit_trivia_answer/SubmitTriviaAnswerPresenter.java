package interface_adapter.submit_trivia_answer;

import interface_adapter.trivia.TriviaState;
import interface_adapter.trivia.TriviaViewModel;
import use_case.submit_trivia_answer.SubmitTriviaAnswerOutputBoundary;
import use_case.submit_trivia_answer.SubmitTriviaAnswerOutputData;

import java.util.List;

public class SubmitTriviaAnswerPresenter implements SubmitTriviaAnswerOutputBoundary {
    private final TriviaViewModel viewModel;

    public SubmitTriviaAnswerPresenter(TriviaViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SubmitTriviaAnswerOutputData output) {
        TriviaState state = viewModel.getState();
        state.setError("");
        if (output.isCorrect()) {
            state.setFeedback("Correct! Score: " + output.getScore() + " / " + output.getTotalQuestions());
        } else {
            state.setFeedback("Incorrect. The answer was " + output.getCorrectAnswer()
                    + ". Score: " + output.getScore() + " / " + output.getTotalQuestions());
        }

        if (output.isQuizComplete()) {
            state.setQuizComplete(true);
            state.setQuestionLabel("Quiz complete");
            state.setQuestionText("Final score: " + output.getScore() + " / "
                    + output.getTotalQuestions() + ". Click Review results to see your answers.");
            state.setOptions(List.of());
        } else {
            state.setQuestionLabel("Question " + output.getNextQuestionNumber()
                    + " of " + output.getTotalQuestions());
            state.setQuestionText(output.getNextQuestionText());
            state.setOptions(output.getNextOptions());
        }
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
