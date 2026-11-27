package use_case;

import entity.TriviaQuestion;
import entity.TriviaSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Shared fake data for the trivia interactor tests.
 */
public final class TriviaTestData {
    private TriviaTestData() {
    }

    /** Makes n questions named "Q1", "Q2", ... where "Right" is always the correct answer. */
    public static List<TriviaQuestion> questions(int n) {
        List<TriviaQuestion> questions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            questions.add(new TriviaQuestion("Q" + (i + 1),
                    List.of("Right", "Wrong1", "Wrong2", "Wrong3"), "Right", "science", "easy"));
        }
        return questions;
    }

    /** A session where every question has already been answered with the given answers. */
    public static TriviaSession finishedSession(List<String> answers) {
        TriviaSession session = new TriviaSession(questions(TriviaSession.QUESTION_COUNT));
        for (String answer : answers) {
            session.submitAnswer(answer);
        }
        return session;
    }
}
