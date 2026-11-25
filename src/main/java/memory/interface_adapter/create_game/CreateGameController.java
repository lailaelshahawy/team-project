package memory.interface_adapter.create_game;

import memory.entity.Difficulty;
import memory.use_case.create_game.CreateGameInputBoundary;
import memory.use_case.create_game.CreateGameInputData;

/**
 * The controller for the Create Game Use Case.
 */
public class CreateGameController {

    private final CreateGameInputBoundary createGameUseCaseInteractor;

    public CreateGameController(CreateGameInputBoundary createGameUseCaseInteractor) {
        this.createGameUseCaseInteractor = createGameUseCaseInteractor;
    }

    /**
     * Executes the Create Game Use Case.
     * @param difficulty the difficulty the user selects for a game
     */
    public void execute(Difficulty difficulty) {
        final CreateGameInputData createGameInputData =
            new CreateGameInputData(difficulty);

        createGameUseCaseInteractor.execute(createGameInputData);
    }
}
