package memory.entity;

import javax.swing.*;
import java.awt.*;
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
    public MemoryGame(Difficulty difficulty, Board board) {
        this.gameId = UUID.randomUUID().toString();
        this.difficulty = difficulty;
        /*
        Score begins at 5 and reduces each time a mistake is made
         */
        this.score = 5;
        this.board = board;
    }

    public int getScore() {
        return this.score;
    }

    public Board getBoard() {
        return this.board;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }
}
