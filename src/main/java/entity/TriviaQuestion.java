package entity;

import java.util.List;

/**
 * A multiple choice trivia question with four distinct answer options.
 */
public class TriviaQuestion {
    private final String questionText;
    private final List<String> options;
    private final String correctAnswer;
    private final String category;
    private final String difficulty;

    public TriviaQuestion(String questionText, List<String> options, String correctAnswer,
                          String category, String difficulty) {
        this.questionText = questionText;
        this.options = List.copyOf(options);
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;

        if (this.options.size() != 4) {
            throw new IllegalArgumentException("A question must have four options.");
        }

        for (int i = 0; i < this.options.size(); i++) {
            for (int j = i + 1; j < this.options.size(); j++) {
                if (this.options.get(i).equals(this.options.get(j))) {
                    throw new IllegalArgumentException("Answer options must be distinct.");
                }
            }
        }

        if (correctAnswer == null || !this.options.contains(correctAnswer)) {
            throw new IllegalArgumentException("The correct answer must be one of the options.");
        }
    }

    public boolean isCorrect(String selectedAnswer) {
        return correctAnswer.equals(selectedAnswer);
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }
}