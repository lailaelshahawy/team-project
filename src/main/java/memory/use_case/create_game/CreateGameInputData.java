package memory.use_case.create_game;

import memory.entity.Difficulty;

/**
 * The input data for the "create game" use case. Here the data is the game difficulty.
 */
public class CreateGameInputData {
    private final Difficulty difficulty;

    public CreateGameInputData(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
