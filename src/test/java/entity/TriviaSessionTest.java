package entity;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class TriviaSessionTest {

    /** Makes n questions where "Right" is always the correct answer. */
    private List<TriviaQuestion> makeQuestions(int n) {
        List<TriviaQuestion> questions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            questions.add(new TriviaQuestion("Question " + (i + 1),
                    List.of("Right", "Wrong1", "Wrong2", "Wrong3"), "Right", "science", "easy"));
        }
        return questions;
    }

    @Test
    public void rejectsFewerThanTenQuestions() {
        assertThrows(IllegalArgumentException.class, () -> new TriviaSession(makeQuestions(9)));
    }

    @Test
    public void rejectsMoreThanTenQuestions() {
        assertThrows(IllegalArgumentException.class, () -> new TriviaSession(makeQuestions(11)));
    }

    @Test
    public void startsOnFirstQuestionWithNoScore() {
        List<TriviaQuestion> questions = makeQuestions(10);
        TriviaSession session = new TriviaSession(questions);

        assertSame(questions.get(0), session.getCurrentQuestion());
        assertEquals(0, session.getScore());
        assertEquals(0, session.getAnsweredCount());
        assertFalse(session.isComplete());
    }

    @Test
    public void correctAnswerScoresAndAdvances() {
        List<TriviaQuestion> questions = makeQuestions(10);
        TriviaSession session = new TriviaSession(questions);

        assertTrue(session.submitAnswer("Right"));
        assertEquals(1, session.getScore());
        assertEquals(1, session.getAnsweredCount());
        assertSame(questions.get(1), session.getCurrentQuestion());
    }

    @Test
    public void incorrectAnswerAdvancesWithoutScoring() {
        List<TriviaQuestion> questions = makeQuestions(10);
        TriviaSession session = new TriviaSession(questions);

        assertFalse(session.submitAnswer("Wrong1"));
        assertEquals(0, session.getScore());
        assertSame(questions.get(1), session.getCurrentQuestion());
    }

    @Test
    public void rejectsAnswerThatIsNotAnOptionAndDoesNotAdvance() {
        TriviaSession session = new TriviaSession(makeQuestions(10));

        assertThrows(IllegalArgumentException.class, () -> session.submitAnswer("Not an option"));
        assertEquals(0, session.getAnsweredCount());
    }

    @Test
    public void rejectsMissingAnswer() {
        TriviaSession session = new TriviaSession(makeQuestions(10));

        assertThrows(IllegalArgumentException.class, () -> session.submitAnswer(null));
        assertEquals(0, session.getAnsweredCount());
    }

    @Test
    public void completesAfterTenAnswersWithCorrectScore() {
        TriviaSession session = new TriviaSession(makeQuestions(10));

        for (int i = 0; i < 10; i++) {
            session.submitAnswer(i < 7 ? "Right" : "Wrong2");
        }

        assertTrue(session.isComplete());
        assertEquals(7, session.getScore());
        assertEquals(10, session.getAnsweredCount());
    }

    @Test
    public void cannotAnswerOrGetQuestionAfterComplete() {
        TriviaSession session = new TriviaSession(makeQuestions(10));
        for (int i = 0; i < 10; i++) {
            session.submitAnswer("Right");
        }

        assertThrows(IllegalStateException.class, () -> session.submitAnswer("Right"));
        assertThrows(IllegalStateException.class, session::getCurrentQuestion);
    }

    @Test
    public void keepsQuestionsAndAnswersInOrderForReview() {
        List<TriviaQuestion> questions = makeQuestions(10);
        TriviaSession session = new TriviaSession(questions);
        session.submitAnswer("Right");
        session.submitAnswer("Wrong3");

        assertEquals(questions, session.getQuestions());
        assertEquals(List.of("Right", "Wrong3"), session.getSubmittedAnswers());
    }
}
