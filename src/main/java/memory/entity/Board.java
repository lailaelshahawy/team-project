package memory.entity;

/**
 * An entity representing a memory game board which is a grid. Board layout is determined based on game difficulty.
 * Boards have rows, columns and cards. There are three possible board layouts: 3x4, 4x4 or 4x6 associated with EASY,
 * MEDIUM and HARD games respectively. A board is created by a MemoryGame.
 */
public class Board {

    private final int rows;
    private final int columns;

    /**
     * Creates a new board with the given non-empty difficulty.
     * @param rows the number of rows the board should have
     * @param columns the number of columns the board should have
     * @throws IllegalArgumentException if the difficulty is empty
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }
}
