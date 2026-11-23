package login_interface_adapter.logout;

import login_interface_adapter.ViewManagerModel;
import login_interface_adapter.logged_in.LoggedInState;
import login_interface_adapter.logged_in.LoggedInViewModel;
import login_interface_adapter.login.LoginState;
import login_interface_adapter.login.LoginViewModel;
import login_use_case.logout.LogoutOutputBoundary;
import login_use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // We need to switch to the login view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string.

        // have prepareSuccessView update the LoggedInState
        // 1. get the LoggedInState out of the appropriate View Model,
        final LoggedInState loggedInState = loggedInViewModel.getState();
        // 2. set the username in the state to the empty string
        loggedInState.setUsername(response.getUsername());
        // 3. notify so that the View that is listening is updated.
        this.loggedInViewModel.notifyStateChanged();

        // have prepareSuccessView update the LoginState
        // 1. get the LoginState out of the appropriate View Model,
        final LoginState loginState = loginViewModel.getState();
        // 2. set the username in the state to be the username of the user that just logged out,
        loginState.setUsername(response.getUsername());
        // 3. notify so that the View that is listening is updated.
        loginViewModel.notifyStateChanged();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(LoginViewModel.VIEW_NAME);
        this.viewManagerModel.notifyViewChanged();
    }
}
