package entity;

import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

public class TriviaQuestionTest {

    @Test
    public void checksCorrectAndIncorrectAnswers() {
        List<String> options = List.of("Toronto", "Ottawa", "Montreal", "Vancouver");
        TriviaQuestion question = new TriviaQuestion(
                "What is the capital of Canada?", options, "Ottawa", "geography", "easy");

        assertTrue(question.isCorrect("Ottawa"));
        assertFalse(question.isCorrect("Toronto"));
    }

    @Test
    public void rejectsDuplicateOptions() {
        List<String> options = List.of("Ottawa", "Toronto", "Montreal", "Ottawa");

        assertThrows(IllegalArgumentException.class, () ->
                new TriviaQuestion("What is the capital of Canada?", options,
                        "Ottawa", "geography", "easy"));

    }

    @Test
    public void rejectsMissingCorrectAnswer() {
        List<String> options = List.of("Toronto", "Halifax", "Montreal", "Vancouver");


        assertThrows(IllegalArgumentException.class, () ->
                new TriviaQuestion("What is the capital of Canada?", options,
                        "Ottawa", "geography", "easy"));
    }
    @Test
    public void storesQuestionDetails() {
        List<String> options = List.of("Toronto", "Ottawa", "Montreal", "Vancouver");
        TriviaQuestion question = new TriviaQuestion(
                "What is the capital of Canada?", options, "Ottawa", "geography", "easy");

        assertEquals("What is the capital of Canada?", question.getQuestionText());
        assertEquals(options, question.getOptions());
        assertEquals("Ottawa", question.getCorrectAnswer());
        assertEquals("geography", question.getCategory());
        assertEquals("easy", question.getDifficulty());
    }

    @Test
    public void rejectsThreeOptions() {
        List<String> options = List.of("Toronto", "Ottawa", "Montreal");

        assertThrows(IllegalArgumentException.class, () ->
                new TriviaQuestion("What is the capital of Canada?", options,
                        "Ottawa", "geography", "easy"));
    }
}