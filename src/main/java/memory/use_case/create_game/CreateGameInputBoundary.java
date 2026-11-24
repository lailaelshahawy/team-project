package memory.use_case.create_game;


/**
 * Input Boundary for actions which are related to creating a memory game.
 */
public interface CreateGameInputBoundary {
    /**
     * Executes the create game use case.
     * @param createGameInputData the input data
     */
    void execute(CreateGameInputData createGameInputData);
}
