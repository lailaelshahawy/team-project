package interface_adapter.start_trivia_quiz;

import interface_adapter.trivia.TriviaState;
import interface_adapter.trivia.TriviaViewModel;
import use_case.start_trivia_quiz.StartTriviaQuizOutputBoundary;
import use_case.start_trivia_quiz.StartTriviaQuizOutputData;

public class StartTriviaQuizPresenter implements StartTriviaQuizOutputBoundary {
    private final TriviaViewModel viewModel;

    public StartTriviaQuizPresenter(TriviaViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(StartTriviaQuizOutputData output) {
        // A new quiz starts from a blank state, clearing any old feedback or review.
        TriviaState state = new TriviaState();
        state.setQuestionLabel("Question " + output.getQuestionNumber()
                + " of " + output.getTotalQuestions());
        state.setQuestionText(output.getQuestionText());
        state.setOptions(output.getOptions());
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        TriviaState state = new TriviaState();
        state.setError(message);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }
}
