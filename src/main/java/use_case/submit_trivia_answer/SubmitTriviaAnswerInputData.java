package use_case.submit_trivia_answer;

public class SubmitTriviaAnswerInputData {
    private final String selectedAnswer;

    public SubmitTriviaAnswerInputData(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }
}
