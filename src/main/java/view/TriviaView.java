package view;

import interface_adapter.review_trivia_results.ReviewTriviaResultsController;
import interface_adapter.start_trivia_quiz.StartTriviaQuizController;
import interface_adapter.submit_trivia_answer.SubmitTriviaAnswerController;
import interface_adapter.trivia.TriviaState;
import interface_adapter.trivia.TriviaViewModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * The trivia screen. Sends user actions to the three controllers and redraws itself
 * whenever the TriviaViewModel announces a change.
 */
public class TriviaView extends JPanel implements PropertyChangeListener {
    private final TriviaViewModel viewModel;
    private final SubmitTriviaAnswerController submitController;
    private final ReviewTriviaResultsController reviewController;

    private final JComboBox<String> category = new JComboBox<>(new String[]{
            "science", "geography", "history", "music", "film_and_tv", "sport_and_leisure",
            "arts_and_literature", "food_and_drink", "general_knowledge", "society_and_culture"});
    private final JComboBox<String> difficulty = new JComboBox<>(new String[]{"easy", "medium", "hard"});
    private final JButton startButton = new JButton("Start quiz");

    private final JLabel questionLabel = new JLabel(" ");
    private final JTextArea questionText = new JTextArea(3, 40);
    private final JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
    private final List<JRadioButton> optionButtons = new ArrayList<>();
    private final JButton submitButton = new JButton("Submit answer");
    private final JButton reviewButton = new JButton("Review results");
    private final JLabel feedbackLabel = new JLabel(" ");
    private final JLabel errorLabel = new JLabel(" ");
    private final JTextArea reviewArea = new JTextArea(12, 40);
    private final JScrollPane reviewScroll = new JScrollPane(reviewArea);

    public TriviaView(StartTriviaQuizController startController,
                      SubmitTriviaAnswerController submitController,
                      ReviewTriviaResultsController reviewController,
                      TriviaViewModel viewModel) {
        super(new BorderLayout(8, 8));
        this.viewModel = viewModel;
        this.submitController = submitController;
        this.reviewController = reviewController;
        viewModel.addPropertyChangeListener(this);

        JPanel settings = new JPanel();
        settings.add(new JLabel("Category"));
        settings.add(category);
        settings.add(new JLabel("Difficulty"));
        settings.add(difficulty);
        settings.add(startButton);
        add(settings, BorderLayout.NORTH);

        questionText.setEditable(false);
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        errorLabel.setForeground(Color.RED);

        JPanel actions = new JPanel();
        actions.add(submitButton);
        actions.add(reviewButton);

        JPanel quiz = new JPanel();
        quiz.setLayout(new BoxLayout(quiz, BoxLayout.Y_AXIS));
        for (JComponent part : new JComponent[]{questionLabel, questionText, optionsPanel,
                actions, feedbackLabel, errorLabel}) {
            part.setAlignmentX(LEFT_ALIGNMENT);
            quiz.add(part);
        }
        add(quiz, BorderLayout.CENTER);

        // The review box stays hidden until there are results to show.
        reviewArea.setEditable(false);
        reviewScroll.setVisible(false);
        add(reviewScroll, BorderLayout.SOUTH);

        submitButton.setEnabled(false);
        reviewButton.setEnabled(false);

        startButton.addActionListener(event -> startQuiz(startController));
        submitButton.addActionListener(event -> submitController.submit(getSelectedAnswer()));
        reviewButton.addActionListener(event -> reviewController.review());
    }

    /** The API call is slow, so run it off the Swing thread. */
    private void startQuiz(StartTriviaQuizController startController) {
        String selectedCategory = (String) category.getSelectedItem();
        String selectedDifficulty = (String) difficulty.getSelectedItem();
        startButton.setEnabled(false);
        submitButton.setEnabled(false);
        questionLabel.setText("Loading questions…");
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                startController.start(selectedCategory, selectedDifficulty);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                } catch (ExecutionException ex) {
                    errorLabel.setText("Could not start the quiz. Please try again.");
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    errorLabel.setText("Quiz loading was interrupted.");
                }
                startButton.setEnabled(true);
            }
        }.execute();
    }

    private String getSelectedAnswer() {
        for (JRadioButton button : optionButtons) {
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // The Start use case runs on a background thread, so redraw on the Swing thread.
        SwingUtilities.invokeLater(() -> render(viewModel.getState()));
    }

    private void render(TriviaState state) {
        questionLabel.setText(state.getQuestionLabel().isEmpty() ? " " : state.getQuestionLabel());
        questionText.setText(state.getQuestionText());
        feedbackLabel.setText(state.getFeedback().isEmpty() ? " " : state.getFeedback());
        errorLabel.setText(state.getError().isEmpty() ? " " : state.getError());
        reviewArea.setText(state.getReviewText());
        reviewArea.setCaretPosition(0);

        optionsPanel.removeAll();
        optionButtons.clear();
        ButtonGroup group = new ButtonGroup();
        for (String option : state.getOptions()) {
            JRadioButton button = new JRadioButton(option);
            group.add(button);
            optionButtons.add(button);
            optionsPanel.add(button);
        }
        optionsPanel.revalidate();
        optionsPanel.repaint();

        submitButton.setEnabled(!state.getOptions().isEmpty());
        reviewButton.setEnabled(state.isQuizComplete());
        reviewScroll.setVisible(!state.getReviewText().isEmpty());

        // Resize the window to fit the new content so the buttons never get cut off.
        java.awt.Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.pack();
        }
    }
}
