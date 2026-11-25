package memory.interface_adapter.create_game;

import memory.entity.MemoryGame;
import memory.use_case.create_game.CreateGameOutputBoundary;
import memory.use_case.create_game.CreateGameOutputData;

/**
 * The presenter for the Create Game Use Case.
 */
public class CreateGamePresenter implements CreateGameOutputBoundary {

    private final CreateGameViewModel createGameViewModel;

    public CreateGamePresenter(CreateGameViewModel createGameViewModel) {
        this.createGameViewModel = createGameViewModel;
    }

    @Override
    public void prepareSuccessView(CreateGameOutputData outputData) {
        final MemoryGame memoryGame = outputData.getGame();
        createGameViewModel.setErrorMessage("");
        createGameViewModel.setMemoryGame(memoryGame);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        createGameViewModel.setErrorMessage(errorMessage);
    }
}
