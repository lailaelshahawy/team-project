package memory.view;

import memory.interface_adapter.create_game.CreateGameController;
import memory.interface_adapter.create_game.CreateGameViewModel;
import memory.interface_adapter.create_game.CreateGameViewModelListener;
import memory.entity.Difficulty;

import javax.swing.*;
import java.awt.*;

public class CreateGameView extends JPanel implements CreateGameViewModelListener {

    private final CreateGameController createGameController;
    private final CreateGameViewModel createGameViewModel;

    private Difficulty selectedDifficulty = Difficulty.EASY;

    private final JToggleButton easyButton;
    private final JToggleButton mediumButton;
    private final JToggleButton hardButton;

    private final JButton startButton;
    private final JLabel errorMessageLabel;

    public CreateGameView(
            CreateGameController createGameController,
            CreateGameViewModel createGameViewModel) {

        this.createGameController = createGameController;
        this.createGameViewModel = createGameViewModel;

        this.easyButton = new JToggleButton("Easy (3 × 4)");
        this.mediumButton = new JToggleButton("Medium (4 × 4)");
        this.hardButton = new JToggleButton("Hard (4 × 6)");

        this.startButton = new JButton("Start Game");
        this.errorMessageLabel = new JLabel(" ");

        createGameViewModel.addListener(this);

        initializeView();
        initializeButtonListeners();
    }

    private void initializeView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Create Your Game");
        JLabel difficultyLabel = new JLabel("Select a difficulty:");

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Group difficulty buttons so only one can be selected.
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easyButton);
        difficultyGroup.add(mediumButton);
        difficultyGroup.add(hardButton);

        // Select Easy by default.
        easyButton.setSelected(true);

        add(Box.createVerticalStrut(40));
        add(titleLabel);
        add(Box.createVerticalStrut(25));
        add(difficultyLabel);
        add(Box.createVerticalStrut(15));
        add(easyButton);
        add(Box.createVerticalStrut(10));
        add(mediumButton);
        add(Box.createVerticalStrut(10));
        add(hardButton);
        add(Box.createVerticalStrut(25));
        add(startButton);
        add(Box.createVerticalStrut(15));
        add(errorMessageLabel);
    }

    private void initializeButtonListeners() {

        easyButton.addActionListener(e ->
                selectedDifficulty = Difficulty.EASY
        );

        mediumButton.addActionListener(e ->
                selectedDifficulty = Difficulty.MEDIUM
        );

        hardButton.addActionListener(e ->
                selectedDifficulty = Difficulty.HARD
        );

        startButton.addActionListener(e ->
                createGameController.execute(selectedDifficulty)
        );
    }

    @Override
    public void createGameViewModelChanged() {
        errorMessageLabel.setText(createGameViewModel.getErrorMessage());

        if (createGameViewModel.getMemoryGame() != null) {
            // Navigate to MemoryGameView here later.
        }
    }
}