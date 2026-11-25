package memory.interface_adapter.create_game;

import memory.entity.MemoryGame;
import memory.interface_adapter.memory_game.MemoryGameViewModel;
import memory.use_case.create_game.CreateGameOutputBoundary;
import memory.use_case.create_game.CreateGameOutputData;
import memory.interface_adapter.ViewManagerModel;

/**
 * The presenter for the Create Game Use Case.
 */
public class CreateGamePresenter implements CreateGameOutputBoundary {

    private final CreateGameViewModel createGameViewModel;
    private final MemoryGameViewModel memoryGameViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateGamePresenter(CreateGameViewModel createGameViewModel, MemoryGameViewModel memoryGameViewModel, ViewManagerModel viewManagerModel) {
        this.createGameViewModel = createGameViewModel;
        this.memoryGameViewModel = memoryGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateGameOutputData outputData) {
        final MemoryGame memoryGame = outputData.getGame();
        createGameViewModel.setErrorMessage("");
        memoryGameViewModel.setMemoryGame(memoryGame);

        viewManagerModel.setState("MEMORY_GAME");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        createGameViewModel.setErrorMessage(errorMessage);
    }
}
