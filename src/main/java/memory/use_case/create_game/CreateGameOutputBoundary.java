package memory.use_case.create_game;

/**
 * The output boundary for the Create Game use case
 */
public interface CreateGameOutputBoundary {
    /**
     * Prepares the success view for the Create Game Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(CreateGameOutputData outputData);

    /**
     * Prepares the failure view for the Create Game Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
