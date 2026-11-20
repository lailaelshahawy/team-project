package use_case.start_trivia_quiz;

public interface StartTriviaQuizOutputBoundary {
    void prepareSuccessView(StartTriviaQuizOutputData output);
    void prepareFailView(String message);
}
