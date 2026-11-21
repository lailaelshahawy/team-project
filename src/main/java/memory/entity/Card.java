package memory.entity;

/**
 * An entity representing a card on the board and whether it is face down or face up. Cards have pair Ids to show their
 * matches.
 */
public class Card {
    private final int pairId;
    private boolean faceUp;
    private boolean matched;
    private String imagePath;

    /**
     * Creates a new card with a given pair ID. By default cards are face down (faceUp = false)
     * @param pairId which is an ID representing the pair a card belongs to
     */
    public Card(int pairId, String imagePath){
        this.pairId = pairId;
        this.faceUp = false;
        this.matched = false;
        this.imagePath = imagePath;
    }

    /**
     * Returns whether this card is face up
     * @return true if the card is face up and false otherwise
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    // A setter for whether a card is face up
    public void setFaceUp(boolean faceUp){
        this.faceUp = faceUp;
    }

    // Gets the pair ID for a card
    public int getPairId(){
        return pairId;
    }

    /**
     * Returns whether this card has been matched to its corresponding pair
     * @return True if the card has been matched and false otherwise
     */
    public boolean isMatched(){
        return matched;
    }

    // Setter which updates whether a card has been matched to its corresponding pair
    public void setMatched(boolean matched){
        this.matched = matched;
    }

    /**
     * A method which compares two card IDs to see if the cards are members of the same pair
     * @return True if the cards match and false otherwise
     */
    public boolean checkMatch(Card card){
        return this.pairId == card.pairId;
    }

    /**
     * A getter for the image path associated with each card. Cards of the same pair have the same image path
     */
    public String getImagePath(){
        return imagePath;
    }
}


