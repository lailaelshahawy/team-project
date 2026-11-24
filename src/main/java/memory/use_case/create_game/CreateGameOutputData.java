package memory.use_case.create_game;

import memory.entity.MemoryGame;

/**
 * Holds the information returned after creating a new game.
 */
public class CreateGameOutputData {
    private final MemoryGame game;

    public CreateGameOutputData(MemoryGame game) {
        this.game = game;
    }

    /**
     * A method that returns a Memory Game
     * @return the created Memory Game
     */
    public MemoryGame getGame() {
        return game;
    }
}

