package memory.game;

import memory.data_access.ImageDataAccessObject;
import memory.interface_adapter.ViewManager;
import memory.interface_adapter.ViewManagerModel;
import memory.interface_adapter.create_game.CreateGameController;
import memory.interface_adapter.create_game.CreateGamePresenter;
import memory.interface_adapter.create_game.CreateGameViewModel;
import memory.use_case.create_game.CreateGameInteractor;
import memory.interface_adapter.memory_game.MemoryGameViewModel;
import memory.view.CreateGameView;
import memory.view.MemoryGameView;

import javax.swing.JFrame;

public class MemoryGameBuilder {

    private static final String CREATE_GAME_VIEW = "CREATE_GAME";
    private static final String MEMORY_GAME_VIEW = "MEMORY_GAME";

    public JFrame build() {

        // Create the ViewModel.
        CreateGameViewModel createGameViewModel =
                new CreateGameViewModel();

        // Create the MemoryGameViewModel.
        MemoryGameViewModel memoryGameViewModel = new MemoryGameViewModel();

        // Create the viewManagerModel.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(viewManagerModel);

        // Create the Presenter.
        CreateGamePresenter createGamePresenter =
                new CreateGamePresenter(createGameViewModel, memoryGameViewModel, viewManagerModel);

        // Create the Data Access Object.
        ImageDataAccessObject imageDataAccessObject =
                new ImageDataAccessObject();

        // Create the Interactor.
        CreateGameInteractor createGameInteractor =
                new CreateGameInteractor(
                        createGamePresenter,
                        imageDataAccessObject
                );

        // Create the Controller.
        CreateGameController createGameController =
                new CreateGameController(createGameInteractor);

        // Create the Create Game View.
        CreateGameView createGameView =
                new CreateGameView(
                        createGameController,
                        createGameViewModel
                );
        // Create the Memory Game View.
        MemoryGameView memoryGameView =
                new MemoryGameView(
                        memoryGameViewModel
                );

        // Register the Create Game screen.
        viewManager.addView(CREATE_GAME_VIEW, createGameView);

        // Register the Memory Game Screen.
        viewManager.addView(MEMORY_GAME_VIEW, memoryGameView);

        // Display the initial screen.
        viewManagerModel.setState(CREATE_GAME_VIEW);

        // Create the application window.
        JFrame frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(viewManager);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        return frame;
    }
}
