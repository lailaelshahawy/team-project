package memory.game;

import memory.data_access.ImageDataAccessObject;
import memory.interface_adapter.ViewManager;
import memory.interface_adapter.ViewManagerModel;
import memory.interface_adapter.create_game.CreateGameController;
import memory.interface_adapter.create_game.CreateGamePresenter;
import memory.interface_adapter.create_game.CreateGameViewModel;
import memory.use_case.create_game.CreateGameInteractor;
import memory.view.CreateGameView;

import javax.swing.JFrame;

public class MemoryGameBuilder {

    private static final String CREATE_GAME_VIEW = "CREATE_GAME";

    public JFrame build() {

        // 1. Create the ViewModel.
        CreateGameViewModel createGameViewModel =
                new CreateGameViewModel();

        // 2. Create the Presenter.
        CreateGamePresenter createGamePresenter =
                new CreateGamePresenter(createGameViewModel);

        // 3. Create the Data Access Object.
        ImageDataAccessObject imageDataAccessObject =
                new ImageDataAccessObject();

        // 4. Create the Interactor.
        CreateGameInteractor createGameInteractor =
                new CreateGameInteractor(
                        createGamePresenter,
                        imageDataAccessObject
                );

        // 5. Create the Controller.
        CreateGameController createGameController =
                new CreateGameController(createGameInteractor);

        // 6. Create the View.
        CreateGameView createGameView =
                new CreateGameView(
                        createGameController,
                        createGameViewModel
                );

        // 7. Create the View Manager.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(viewManagerModel);

        // Register the Create Game screen.
        viewManager.addView(CREATE_GAME_VIEW, createGameView);

        // Display the initial screen.
        viewManagerModel.setState(CREATE_GAME_VIEW);

        // 8. Create the application window.
        JFrame frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(viewManager);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        return frame;
    }
}
