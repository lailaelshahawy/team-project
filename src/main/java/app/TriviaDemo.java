package app;

import data_access.TriviaApiDataAccessObject;
import interface_adapter.start_trivia_quiz.*;
import use_case.start_trivia_quiz.StartTriviaQuizInteractor;
import view.StartTriviaQuizView;
import javax.swing.*;

public class TriviaDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartTriviaQuizViewModel model = new StartTriviaQuizViewModel();
            StartTriviaQuizPresenter presenter = new StartTriviaQuizPresenter(model);
            StartTriviaQuizInteractor interactor = new StartTriviaQuizInteractor(
                    new TriviaApiDataAccessObject(), presenter);
            JFrame frame = new JFrame("Start Trivia Quiz");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(new StartTriviaQuizView(new StartTriviaQuizController(interactor), model));
            frame.setSize(650, 330);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
