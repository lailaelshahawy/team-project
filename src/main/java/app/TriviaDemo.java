package app;

import data_access.InMemoryTriviaSessionDataAccessObject;
import data_access.TriviaApiDataAccessObject;
import interface_adapter.review_trivia_results.ReviewTriviaResultsController;
import interface_adapter.review_trivia_results.ReviewTriviaResultsPresenter;
import interface_adapter.start_trivia_quiz.StartTriviaQuizController;
import interface_adapter.start_trivia_quiz.StartTriviaQuizPresenter;
import interface_adapter.submit_trivia_answer.SubmitTriviaAnswerController;
import interface_adapter.submit_trivia_answer.SubmitTriviaAnswerPresenter;
import interface_adapter.trivia.TriviaViewModel;
import use_case.review_trivia_results.ReviewTriviaResultsInteractor;
import use_case.start_trivia_quiz.StartTriviaQuizInteractor;
import use_case.submit_trivia_answer.SubmitTriviaAnswerInteractor;
import view.TriviaView;

import javax.swing.*;

/**
 * Main component for the trivia feature: builds the CA engine and connects the pieces.
 */
public class TriviaDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TriviaViewModel viewModel = new TriviaViewModel();

            // One session store shared by all three use cases.
            InMemoryTriviaSessionDataAccessObject sessionStore = new InMemoryTriviaSessionDataAccessObject();

            StartTriviaQuizInteractor startInteractor = new StartTriviaQuizInteractor(
                    new TriviaApiDataAccessObject(), sessionStore, new StartTriviaQuizPresenter(viewModel));
            SubmitTriviaAnswerInteractor submitInteractor = new SubmitTriviaAnswerInteractor(
                    sessionStore, new SubmitTriviaAnswerPresenter(viewModel));
            ReviewTriviaResultsInteractor reviewInteractor = new ReviewTriviaResultsInteractor(
                    sessionStore, new ReviewTriviaResultsPresenter(viewModel));

            TriviaView view = new TriviaView(
                    new StartTriviaQuizController(startInteractor),
                    new SubmitTriviaAnswerController(submitInteractor),
                    new ReviewTriviaResultsController(reviewInteractor),
                    viewModel);

            JFrame frame = new JFrame("Trivia Challenge");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(view);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
