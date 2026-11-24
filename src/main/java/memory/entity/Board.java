package memory.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity representing a memory game board which is a grid. Board layout is determined based on game difficulty.
 * Boards have rows, columns and cards. There are three possible board layouts: 3x4, 4x4 or 4x6 associated with EASY,
 * MEDIUM and HARD games respectively. A board is created by a MemoryGame.
 */
public class Board {
    private final int rows;
    private final int columns;
    private final List<List<Card>> cardsLayout;

    /**
     * Creates a new board with the given dimensions.
     *
     * @param rows the number of rows the board should have
     * @param columns the number of columns the board should have
     * @throws IllegalArgumentException if dimensions are non-positive
     * or the total number of cards is odd
     */
    public Board(int rows, int columns, List<Card> cards) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException(
                    "Number of rows and columns must be positive"
            );
        }
        if (rows * columns != cards.size()) {
            throw new IllegalArgumentException(
                    "Number of cards must match board dimensions"
            );
        }

        this.rows = rows;
        this.columns = columns;
        this.cardsLayout = createBoard(cards);

    }
    /**
     * Helper function for positioning cards from a list on the board.
     */
    private List<List<Card>> createBoard(List<Card> cards) {

        // Create the outer board so that inner rows can be added to it
        final List<List<Card>> gridLayout = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Card> newRow = new ArrayList<>();

            // Add a card to each cell in the grid
            for (int j = 0; j < columns; j++) {
                newRow.add(cards.get(i * columns + j));
            }

            gridLayout.add(newRow);
        }

        return gridLayout;
    }

    /**
     * Returns the layout of the board, where each integer represents a card pair ID.
     * Matching cards have the same pair ID.
     *
     * @return the 2D list representing the board layout
     */
    public List<List<Card>> getLayout(){
        return cardsLayout;
    }
}
