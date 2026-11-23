package memory.data_access;

import java.util.List;

public class ImageDataAccess implements ImageDataAccessInterface {
    private final List<String> images = List.of(
            "/images/cat.png",
            "/images/dog.png",
            "/images/flower.png",
            "/images/beach.png",
            "/images/sunset.png",
            "/images/pizza.png"
    );

    public List<String> getImages() {
        return images;
    }
}
