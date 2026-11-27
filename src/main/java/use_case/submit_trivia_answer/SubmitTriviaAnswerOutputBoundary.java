package use_case.submit_trivia_answer;

public interface SubmitTriviaAnswerOutputBoundary {
    void prepareSuccessView(SubmitTriviaAnswerOutputData output);

    void prepareFailView(String message);
}
