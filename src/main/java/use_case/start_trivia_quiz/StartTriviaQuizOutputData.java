package use_case.start_trivia_quiz;

import java.util.List;

public class StartTriviaQuizOutputData {
    private final String questionText;
    private final List<String> options;

    public StartTriviaQuizOutputData(String questionText, List<String> options) {
        this.questionText = questionText;
        this.options = List.copyOf(options);
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }
}
