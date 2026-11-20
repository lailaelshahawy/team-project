package memory.entity;

import java.util.UUID;

/**
 * An entity representing a memory game. Memory games have a game id, difficulty, score and board.
 */
public class MemoryGame {
    private final String gameId;
    private final Difficulty difficulty;
    private final Board board;
    private int score;

    /**
     * Creates a new memory game with an auto-generated id and given difficulty;
     * @param difficulty the game difficulty
     */
    public MemoryGame(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.gameId = UUID.randomUUID().toString();
        this.score = 5;
        this.board = new Board(difficulty.getRows(),
                difficulty.getColumns());
    }
}
