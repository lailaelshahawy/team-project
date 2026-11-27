package use_case.submit_trivia_answer;

import java.util.List;

/**
 * The result of answering one question: whether it was right, the running score,
 * and either the next question or the fact that the quiz is finished.
 * When the quiz is complete, the next-question fields are empty.
 */
public class SubmitTriviaAnswerOutputData {
    private final boolean correct;
    private final String correctAnswer;
    private final int score;
    private final int totalQuestions;
    private final boolean quizComplete;
    private final int nextQuestionNumber;
    private final String nextQuestionText;
    private final List<String> nextOptions;

    public SubmitTriviaAnswerOutputData(boolean correct, String correctAnswer, int score,
                                        int totalQuestions, boolean quizComplete,
                                        int nextQuestionNumber, String nextQuestionText,
                                        List<String> nextOptions) {
        this.correct = correct;
        this.correctAnswer = correctAnswer;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.quizComplete = quizComplete;
        this.nextQuestionNumber = nextQuestionNumber;
        this.nextQuestionText = nextQuestionText;
        this.nextOptions = List.copyOf(nextOptions);
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public boolean isQuizComplete() {
        return quizComplete;
    }

    public int getNextQuestionNumber() {
        return nextQuestionNumber;
    }

    public String getNextQuestionText() {
        return nextQuestionText;
    }

    public List<String> getNextOptions() {
        return nextOptions;
    }
}
