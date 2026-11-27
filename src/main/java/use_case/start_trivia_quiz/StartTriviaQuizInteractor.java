package use_case.start_trivia_quiz;

import entity.TriviaQuestion;
import entity.TriviaSession;

public class StartTriviaQuizInteractor implements StartTriviaQuizInputBoundary {
    private final StartTriviaQuizDataAccessInterface questionDataAccess;
    private final StartTriviaQuizSessionDataAccessInterface sessionDataAccess;
    private final StartTriviaQuizOutputBoundary presenter;

    public StartTriviaQuizInteractor(StartTriviaQuizDataAccessInterface questionDataAccess,
                                     StartTriviaQuizSessionDataAccessInterface sessionDataAccess,
                                     StartTriviaQuizOutputBoundary presenter) {
        this.questionDataAccess = questionDataAccess;
        this.sessionDataAccess = sessionDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(StartTriviaQuizInputData input) {
        TriviaSession session;
        try {
            session = new TriviaSession(questionDataAccess.fetchQuestions(
                    input.getCategory(), input.getDifficulty()));
        } catch (IllegalStateException | IllegalArgumentException ex) {
            presenter.prepareFailView(ex.getMessage());
            return;
        }
        sessionDataAccess.saveSession(session);

        TriviaQuestion first = session.getCurrentQuestion();
        presenter.prepareSuccessView(new StartTriviaQuizOutputData(
                session.getAnsweredCount() + 1, TriviaSession.QUESTION_COUNT,
                first.getQuestionText(), first.getOptions()));
    }
}
