
package memory.view;

import memory.entity.Card;
import memory.entity.MemoryGame;
import memory.interface_adapter.memory_game.MemoryGameViewModel;
import memory.interface_adapter.memory_game.MemoryGameViewModelListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MemoryGameView extends JPanel
        implements MemoryGameViewModelListener {

    private final MemoryGameViewModel memoryGameViewModel;

    private final JPanel boardPanel;
    private final JLabel scoreLabel;

    public MemoryGameView(MemoryGameViewModel memoryGameViewModel) {

        this.memoryGameViewModel = memoryGameViewModel;

        this.boardPanel = new JPanel();
        this.scoreLabel = new JLabel("Score: 5");

        memoryGameViewModel.addListener(this);

        initializeView();
    }

    private void initializeView() {

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Memory Game", SwingConstants.CENTER);

        add(titleLabel, BorderLayout.NORTH);
        add(scoreLabel, BorderLayout.SOUTH);
        add(boardPanel, BorderLayout.CENTER);
    }

    private void displayBoard(MemoryGame memoryGame) {

        boardPanel.removeAll();

        int rows = memoryGame.getDifficulty().getRows();
        int columns = memoryGame.getDifficulty().getColumns();

        boardPanel.setLayout(new GridLayout(rows, columns, 10, 10));

        List<List<Card>> layout = memoryGame.getBoard().getLayout();

        for (List<Card> row : layout) {
            for (Card card : row) {

                JButton cardButton = new JButton("?");
                cardButton.setPreferredSize(new Dimension(100, 120));

                boardPanel.add(cardButton);
            }
        }

        scoreLabel.setText("Score: " + memoryGame.getScore());

        revalidate();
        repaint();
    }

    @Override
    public void memoryGameViewModelChanged() {

        MemoryGame memoryGame = memoryGameViewModel.getMemoryGame();

        if (memoryGame != null) {
            displayBoard(memoryGame);
        }
    }
}