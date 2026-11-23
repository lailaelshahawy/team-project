package login_interface_adapter.logout;

import login_use_case.login.LoginInputData;
import login_use_case.logout.LogoutInputBoundary;

/**
 * The controller for the Logout Use Case.
 */
public class LogoutController {

    private LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;    }

    /**
     * Executes the Logout Use Case.
     */
    public void execute() {
        logoutUseCaseInteractor.execute();
    }
}
