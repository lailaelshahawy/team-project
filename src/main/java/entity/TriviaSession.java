package entity;

import java.util.List;

public class TriviaSession {
    private final List<TriviaQuestion> questions;

    public TriviaSession(List<TriviaQuestion> questions) {
        if (questions.size() != 10) {
            throw new IllegalArgumentException("A quiz needs ten questions. Please try again.");
        }
        this.questions = List.copyOf(questions);
    }

    public TriviaQuestion getFirstQuestion() {
        return questions.get(0);
    }
}
