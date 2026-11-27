package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * An active trivia quiz of ten questions.
 * Tracks the player's submitted answers, the current question, the score,
 * and whether the quiz is complete.
 */
public class TriviaSession {
    public static final int QUESTION_COUNT = 10;

    private final List<TriviaQuestion> questions;
    private final List<String> submittedAnswers = new ArrayList<>();
    private int score;

    /**
     * Creates a new quiz session.
     *
     * @param questions exactly ten questions, in the order they will be asked
     * @throws IllegalArgumentException if there are not exactly ten questions
     */
    public TriviaSession(List<TriviaQuestion> questions) {
        if (questions.size() != QUESTION_COUNT) {
            throw new IllegalArgumentException("A quiz needs ten questions. Please try again.");
        }
        this.questions = List.copyOf(questions);
    }

    /**
     * Returns the question the player is currently on.
     *
     * @throws IllegalStateException if the quiz is already complete
     */
    public TriviaQuestion getCurrentQuestion() {
        if (isComplete()) {
            throw new IllegalStateException("The quiz is already complete.");
        }
        return questions.get(submittedAnswers.size());
    }

    /**
     * Records the player's answer to the current question and moves to the next one.
     *
     * @param answer the option the player selected
     * @return true if the answer was correct
     * @throws IllegalStateException if the quiz is already complete
     * @throws IllegalArgumentException if the answer is not one of the current question's options
     */
    public boolean submitAnswer(String answer) {
        TriviaQuestion current = getCurrentQuestion();
        if (answer == null || !current.getOptions().contains(answer)) {
            throw new IllegalArgumentException("Please choose one of the answer options.");
        }
        boolean correct = current.isCorrect(answer);
        submittedAnswers.add(answer);
        if (correct) {
            score++;
        }
        return correct;
    }

    public boolean isComplete() {
        return submittedAnswers.size() == questions.size();
    }

    public int getScore() {
        return score;
    }

    /**
     * Returns how many questions have been answered so far (0 to 10).
     */
    public int getAnsweredCount() {
        return submittedAnswers.size();
    }

    public List<TriviaQuestion> getQuestions() {
        return questions;
    }

    public List<String> getSubmittedAnswers() {
        return List.copyOf(submittedAnswers);
    }
}
