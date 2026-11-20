package use_case.start_trivia_quiz;

import entity.TriviaQuestion;

import java.util.List;

public interface StartTriviaQuizDataAccessInterface {
    List<TriviaQuestion> fetchQuestions(String category, String difficulty);
}
