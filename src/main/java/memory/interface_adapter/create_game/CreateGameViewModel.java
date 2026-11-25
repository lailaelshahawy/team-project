package memory.interface_adapter.create_game;

import memory.entity.MemoryGame;

import java.util.ArrayList;
import java.util.List;

/**
 * The View Model for the Create Game View.
 */
public class CreateGameViewModel {

    private final List<CreateGameViewModelListener> listeners =
            new ArrayList<>();

    private String errorMessage = "";
    private MemoryGame memoryGame;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        notifyListeners();
    }

    public MemoryGame getMemoryGame() {
        return memoryGame;
    }

    public void setMemoryGame(MemoryGame memoryGame) {
        this.memoryGame = memoryGame;
        notifyListeners();
    }

    public void addListener(CreateGameViewModelListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (CreateGameViewModelListener listener : listeners) {
            listener.createGameViewModelChanged();
        }
    }
}