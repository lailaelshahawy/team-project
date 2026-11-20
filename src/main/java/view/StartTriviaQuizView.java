package view;

import interface_adapter.start_trivia_quiz.StartTriviaQuizController;
import interface_adapter.start_trivia_quiz.StartTriviaQuizViewModel;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.concurrent.ExecutionException;

public class StartTriviaQuizView extends JPanel {
    private final JComboBox<String> category = new JComboBox<>(new String[]{
            "science", "geography", "history", "music", "film_and_tv", "sport_and_leisure",
            "arts_and_literature", "food_and_drink", "general_knowledge", "society_and_culture"});
    private final JComboBox<String> difficulty = new JComboBox<>(new String[]{"easy", "medium", "hard"});
    private final JButton start = new JButton("Start quiz");
    private final JTextArea message = new JTextArea(3, 36);
    private final JPanel options = new JPanel(new GridLayout(0, 1));

    public StartTriviaQuizView(StartTriviaQuizController controller, StartTriviaQuizViewModel model) {
        super(new BorderLayout(8, 8));
        JPanel settings = new JPanel();
        settings.add(new JLabel("Category"));
        settings.add(category);
        settings.add(new JLabel("Difficulty"));
        settings.add(difficulty);
        settings.add(start);
        add(settings, BorderLayout.NORTH);
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        add(message, BorderLayout.CENTER);
        add(options, BorderLayout.SOUTH);
        start.addActionListener(event -> {
            String selectedCategory = (String) category.getSelectedItem();
            String selectedDifficulty = (String) difficulty.getSelectedItem();
            start.setEnabled(false);
            options.removeAll();
            options.revalidate();
            options.repaint();
            message.setText("Loading questions…");
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                    controller.start(selectedCategory, selectedDifficulty);
                    return null;
                }

                @Override
                protected void done() {
                    try {
                        get();
                        message.setText(model.getMessage());
                        ButtonGroup choices = new ButtonGroup();
                        for (String option : model.getOptions()) {
                            JRadioButton choice = new JRadioButton(option);
                            choices.add(choice);
                            options.add(choice);
                        }
                    } catch (ExecutionException ex) {
                        message.setText("Could not start the quiz. Please try again.");
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        message.setText("Quiz loading was interrupted.");
                    }
                    options.revalidate();
                    options.repaint();
                    start.setEnabled(true);
                }
            }.execute();
        });
    }
}
