package use_case.start_trivia_quiz;

import entity.TriviaSession;

/**
 * Where the Start Trivia Quiz use case stores the quiz it has just created,
 * so that later use cases (Submit Answer, Review Results) can find it.
 */
public interface StartTriviaQuizSessionDataAccessInterface {
    void saveSession(TriviaSession session);
}
