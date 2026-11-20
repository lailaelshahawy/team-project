package use_case.start_trivia_quiz;

import entity.TriviaQuestion;
import entity.TriviaSession;

public class StartTriviaQuizInteractor implements StartTriviaQuizInputBoundary {
    private final StartTriviaQuizDataAccessInterface dataAccess;
    private final StartTriviaQuizOutputBoundary presenter;
    private TriviaSession activeSession;

    public StartTriviaQuizInteractor(StartTriviaQuizDataAccessInterface dataAccess,
                                    StartTriviaQuizOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(StartTriviaQuizInputData input) {
        try {
            activeSession = new TriviaSession(dataAccess.fetchQuestions(
                    input.getCategory(), input.getDifficulty()));
        } catch (IllegalStateException | IllegalArgumentException ex) {
            presenter.prepareFailView(ex.getMessage());
            return;
        }
        TriviaQuestion first = activeSession.getFirstQuestion();
        presenter.prepareSuccessView(new StartTriviaQuizOutputData(first.getQuestionText(), first.getOptions()));
    }
}
