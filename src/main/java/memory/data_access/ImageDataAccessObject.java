package memory.data_access;

import memory.use_case.create_game.ImageDataAccessInterface;

import java.util.List;

public class ImageDataAccessObject implements ImageDataAccessInterface {
    private final List<String> images = List.of(
            "images/cat.png",
            "images/crab.png",
            "images/dog.png",
            "images/fish.png",
            "images/hamster.png",
            "images/hedgehog.png",
            "images/king_julian.png",
            "images/monkey.png",
            "images/plankton.png",
            "images/rabbit.png",
            "images/seal.png",
            "images/spider.png"
    );

    @Override
    public List<String> getImages() {
        return images;
    }

    @Override
    public String getBackCard() {
        return "images/back_of_card.png";
    }
}
