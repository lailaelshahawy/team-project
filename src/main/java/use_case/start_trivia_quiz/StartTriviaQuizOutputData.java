package use_case.start_trivia_quiz;

import java.util.List;

public class StartTriviaQuizOutputData {
    private final int questionNumber;
    private final int totalQuestions;
    private final String questionText;
    private final List<String> options;

    public StartTriviaQuizOutputData(int questionNumber, int totalQuestions,
                                     String questionText, List<String> options) {
        this.questionNumber = questionNumber;
        this.totalQuestions = totalQuestions;
        this.questionText = questionText;
        this.options = List.copyOf(options);
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }
}
