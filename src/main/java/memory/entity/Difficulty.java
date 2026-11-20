package memory.entity;

/**
 * Enum storing possible game difficulties and their associated board sizes.
 */
public enum Difficulty {
    EASY(3,4),
    MEDIUM(4,4),
    HARD(4,6);

    private final int rows;
    private final int columns;

    Difficulty(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
