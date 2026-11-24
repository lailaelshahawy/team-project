package memory.use_case.create_game;

import memory.entity.Board;
import memory.entity.Card;
import memory.entity.MemoryGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import memory.entity.Difficulty;

/**
 * The Create Game Use Case interactor
 */
public class CreateGameInteractor implements CreateGameInputBoundary{
    private final CreateGameOutputBoundary gamePresenter;
    private final ImageDataAccessInterface imageDataAccessObject;

    public CreateGameInteractor(CreateGameOutputBoundary gamePresenter, ImageDataAccessInterface imageDataAccessObject){
        this.gamePresenter = gamePresenter;
        this.imageDataAccessObject = imageDataAccessObject;
    }

    @Override
    public void execute(CreateGameInputData createGameInputData){
        final Difficulty difficulty = createGameInputData.getDifficulty();
        final int rows = difficulty.getRows();
        final int columns = difficulty.getColumns();
        final int totalPairs = (rows * columns) / 2;
        final List<String> images = imageDataAccessObject.getImages();

        if (images == null || images.size() < totalPairs) {
            gamePresenter.prepareFailView(
                    "Not enough images available for the selected difficulty."
            );
            return;
        }
        final List<Card> cards = new ArrayList<>();

        for (int i = 0; i < totalPairs; i++) {
            final String imagePath = images.get(i);

            cards.add(new Card(i, imagePath));
            cards.add(new Card(i, imagePath));
        }

        /*
        Shuffles the cards so that their position on each board is different
         */
        Collections.shuffle(cards);

        /*
        Create a new game board using the shuffled list of cards
         */
        Board board = new Board(rows, columns, cards);

        /*
        Create a new memory game
         */
        MemoryGame memoryGame = new MemoryGame(difficulty, board);

        final CreateGameOutputData createGameOutputData = new CreateGameOutputData(memoryGame);
        gamePresenter.prepareSuccessView(createGameOutputData);

    }



}
