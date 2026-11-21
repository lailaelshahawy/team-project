package memory.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An entity representing a memory game board which is a grid. Board layout is determined based on game difficulty.
 * Boards have rows, columns and cards. There are three possible board layouts: 3x4, 4x4 or 4x6 associated with EASY,
 * MEDIUM and HARD games respectively. A board is created by a MemoryGame.
 */
public class Board {
    private final int rows;
    private final int columns;
    private final List<List<Integer>> cardsLayout;

    /**
     * Creates a new board with the given dimensions.
     *
     * @param rows the number of rows the board should have
     * @param columns the number of columns the board should have
     * @throws IllegalArgumentException if dimensions are non-positive
     * or the total number of cards is odd
     */
    public Board(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Number of rows and columns must be positive");
        }
        if (rows * columns % 2 != 0){
            throw new IllegalArgumentException("There must be an even number of cards");
        }
        this.rows = rows;
        this.columns = columns;
        this.cardsLayout = createBoard();
    }

    /**
     * Helper function for creating a unique game board of cards for matching based on a specified number of rows and
     * columns.
     */
    private List<List<Integer>> createBoard(){
        final int totalCards = rows * columns;
        final int totalPairs = totalCards/2;
        final List<Integer> cardPairIds = new ArrayList<>();

        // This loop generates numbers representing card pair Ids and adds them to cardPairIds
        for (int i = 1; i <= totalPairs; i++){
            cardPairIds.add(i);
            cardPairIds.add(i);
        }

        // Shuffle the card ids so that each newly created board is different
        Collections.shuffle(cardPairIds);

        // Create the outer board so that inner rows can be added to it
        final List<List<Integer>> gridLayout = new ArrayList<>();

        for (int i = 0; i < rows; i++){
            List<Integer> newRow = new ArrayList<>();

            // Adds an id to a cell in the grid. i * columns + j calculates the index of the id for each cell.
            for (int j = 0; j < columns; j++) {
                newRow.add(cardPairIds.get(i * columns + j));
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
    public List<List<Integer>> getLayout(){
        return cardsLayout;
    }
}
