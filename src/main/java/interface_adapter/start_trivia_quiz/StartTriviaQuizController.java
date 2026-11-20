package interface_adapter.start_trivia_quiz;

import use_case.start_trivia_quiz.StartTriviaQuizInputBoundary;
import use_case.start_trivia_quiz.StartTriviaQuizInputData;

public class StartTriviaQuizController {
    private final StartTriviaQuizInputBoundary interactor;

    public StartTriviaQuizController(StartTriviaQuizInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void start(String category, String difficulty) {
        interactor.execute(new StartTriviaQuizInputData(category, difficulty));
    }
}
