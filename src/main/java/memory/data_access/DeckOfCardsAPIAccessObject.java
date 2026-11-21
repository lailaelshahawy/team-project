package memory.data_access;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Retrieves playing card information from the Deck of Cards API.
 */
public class DeckOfCardsAPIAccessObject {
    private final OkHttpClient client;

    public DeckOfCardsApiDataAccessObject() {
        this.client = new OkHttpClient();
    }

    /**
     * Creates a new deck of cards that will be used for all games.
     *
     * @param cards the number of unique cards required
     * @return a list containing the card codes and image URLs
     * @throws IOException if the API request fails
     */
    public List<CardImageData> fetchCards(String cards)

    private static final String CARD_DECK = "https://www.deckofcardsapi.com/api/deck/new/shuffle/?cards=AS,KS,QS," +
            "JS,0D,9C,8H,7S,6D,5C,4H,3S,2D";

    JSONOBJECT deck = new JSONOBJECT(CARD_DECK);

    String deckId = deck.getString("deck_id");



    private final OkHttpClient client = new OkHttpClient();

