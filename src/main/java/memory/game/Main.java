package memory.game;

import javax.swing.*;

/**
 * Builds and launches the memory game application.
 */
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MemoryGameBuilder builder = new MemoryGameBuilder();

            JFrame application = builder.build();

            application.setVisible(true);
        });
    }
}