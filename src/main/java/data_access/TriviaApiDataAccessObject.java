package data_access;

import entity.TriviaQuestion;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import use_case.start_trivia_quiz.StartTriviaQuizDataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriviaApiDataAccessObject implements StartTriviaQuizDataAccessInterface {
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10)).build();

    @Override
    public List<TriviaQuestion> fetchQuestions(String category, String difficulty) {
        String url = "https://the-trivia-api.com/v2/questions?limit=10&categories="
                + URLEncoder.encode(category, StandardCharsets.UTF_8) + "&difficulties="
                + URLEncoder.encode(difficulty, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(20)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new IllegalStateException("Trivia API returned HTTP " + response.statusCode());
            }
            JSONArray rows = new JSONArray(response.body());
            List<TriviaQuestion> questions = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject row = rows.getJSONObject(i);
                String correct = row.getString("correctAnswer");
                JSONArray wrong = row.getJSONArray("incorrectAnswers");
                List<String> options = new ArrayList<>();
                for (int j = 0; j < wrong.length(); j++) {
                    options.add(wrong.getString(j));
                }
                options.add(correct);
                Collections.shuffle(options);
                questions.add(new TriviaQuestion(row.getJSONObject("question").getString("text"),
                        options, correct, row.getString("category"), row.getString("difficulty")));
            }
            return questions;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Question request interrupted.", ex);
        } catch (IOException | JSONException | IllegalArgumentException ex) {
            throw new IllegalStateException("Could not load trivia questions. Please try again.", ex);
        }
    }
}
