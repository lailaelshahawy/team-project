package interface_adapter.submit_trivia_answer;

import use_case.submit_trivia_answer.SubmitTriviaAnswerInputBoundary;
import use_case.submit_trivia_answer.SubmitTriviaAnswerInputData;

public class SubmitTriviaAnswerController {
    private final SubmitTriviaAnswerInputBoundary interactor;

    public SubmitTriviaAnswerController(SubmitTriviaAnswerInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * @param selectedAnswer the option the player picked, or null if they picked nothing
     */
    public void submit(String selectedAnswer) {
        interactor.execute(new SubmitTriviaAnswerInputData(selectedAnswer));
    }
}
