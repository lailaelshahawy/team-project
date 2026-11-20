package use_case.start_trivia_quiz;

public class StartTriviaQuizInputData {
    private final String category;
    private final String difficulty;

    public StartTriviaQuizInputData(String category, String difficulty) {
        this.category = category;
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
