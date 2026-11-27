package use_case.submit_trivia_answer;

import entity.TriviaSession;

public interface SubmitTriviaAnswerDataAccessInterface {
    /**
     * Returns the quiz in progress, or null if no quiz has been started.
     */
    TriviaSession getSession();

    void saveSession(TriviaSession session);
}
