package interface_adapter.start_trivia_quiz;

import java.util.List;

public class StartTriviaQuizViewModel {
    private String message = "";
    private List<String> options = List.of();

    void update(String message, List<String> options) {
        this.message = message;
        this.options = List.copyOf(options);
    }

    public String getMessage() {
        return message;
    }

    public List<String> getOptions() {
        return options;
    }
}
