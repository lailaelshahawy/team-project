package memory.use_case.create_game;

import java.util.List;

/**
 * DAO Interface for the play game use case. Interface is implemented by the ImageDataAccessObject.
 */
public interface ImageDataAccessInterface {
    /**
     * Returns the paths of all available card images
     *
     * @return a list of strings of card image paths
     */
    static List<String> getImages() {
        return null;
    }

    /**
     * Returns the image for the back of the card
     * @return a string path for the image of the back of the card
     */
    String getBackCard();
}

