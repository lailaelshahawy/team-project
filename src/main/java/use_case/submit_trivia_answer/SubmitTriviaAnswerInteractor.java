package use_case.submit_trivia_answer;

import entity.TriviaQuestion;
import entity.TriviaSession;

import java.util.List;

public class SubmitTriviaAnswerInteractor implements SubmitTriviaAnswerInputBoundary {
    private final SubmitTriviaAnswerDataAccessInterface sessionDataAccess;
    private final SubmitTriviaAnswerOutputBoundary presenter;

    public SubmitTriviaAnswerInteractor(SubmitTriviaAnswerDataAccessInterface sessionDataAccess,
                                        SubmitTriviaAnswerOutputBoundary presenter) {
        this.sessionDataAccess = sessionDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(SubmitTriviaAnswerInputData input) {
        TriviaSession session = sessionDataAccess.getSession();
        if (session == null) {
            presenter.prepareFailView("There is no quiz in progress. Start a quiz first.");
            return;
        }

        TriviaQuestion answered;
        boolean correct;
        try {
            answered = session.getCurrentQuestion();
            correct = session.submitAnswer(input.getSelectedAnswer());
        } catch (IllegalStateException | IllegalArgumentException ex) {
            // The entity enforces the rules: quiz already finished, or not a valid option.
            presenter.prepareFailView(ex.getMessage());
            return;
        }
        sessionDataAccess.saveSession(session);

        SubmitTriviaAnswerOutputData output;
        if (session.isComplete()) {
            output = new SubmitTriviaAnswerOutputData(correct, answered.getCorrectAnswer(),
                    session.getScore(), TriviaSession.QUESTION_COUNT, true,
                    0, "", List.of());
        } else {
            TriviaQuestion next = session.getCurrentQuestion();
            output = new SubmitTriviaAnswerOutputData(correct, answered.getCorrectAnswer(),
                    session.getScore(), TriviaSession.QUESTION_COUNT, false,
                    session.getAnsweredCount() + 1, next.getQuestionText(), next.getOptions());
        }
        presenter.prepareSuccessView(output);
    }
}
