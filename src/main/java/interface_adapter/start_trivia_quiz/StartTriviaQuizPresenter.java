package interface_adapter.start_trivia_quiz;

import java.util.List;
import use_case.start_trivia_quiz.StartTriviaQuizOutputBoundary;
import use_case.start_trivia_quiz.StartTriviaQuizOutputData;

public class StartTriviaQuizPresenter implements StartTriviaQuizOutputBoundary {
    private final StartTriviaQuizViewModel model;

    public StartTriviaQuizPresenter(StartTriviaQuizViewModel model) {
        this.model = model;
    }

    @Override
    public void prepareSuccessView(StartTriviaQuizOutputData output) {
        model.update("Question 1 of 10 — " + output.getQuestionText(), output.getOptions());
    }

    @Override
    public void prepareFailView(String message) {
        model.update(message, List.of());
    }
}
