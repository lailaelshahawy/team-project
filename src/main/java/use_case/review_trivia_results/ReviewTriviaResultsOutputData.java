package use_case.review_trivia_results;

import java.util.List;

/**
 * The final score plus one review line per question, in the order they were asked.
 */
public class ReviewTriviaResultsOutputData {
    private final int score;
    private final int totalQuestions;
    private final List<QuestionReview> reviews;

    public ReviewTriviaResultsOutputData(int score, int totalQuestions, List<QuestionReview> reviews) {
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.reviews = List.copyOf(reviews);
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public List<QuestionReview> getReviews() {
        return reviews;
    }

    /**
     * What the player answered for one question, next to the correct answer.
     */
    public static class QuestionReview {
        private final String questionText;
        private final String selectedAnswer;
        private final String correctAnswer;
        private final boolean correct;

        public QuestionReview(String questionText, String selectedAnswer,
                              String correctAnswer, boolean correct) {
            this.questionText = questionText;
            this.selectedAnswer = selectedAnswer;
            this.correctAnswer = correctAnswer;
            this.correct = correct;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getSelectedAnswer() {
            return selectedAnswer;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public boolean isCorrect() {
            return correct;
        }
    }
}
