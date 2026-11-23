package login_use_case.signup;

import login_entity.User;
import login_entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements login_use_case.signup.SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if ("".equals(signupInputData.getPassword())) {
            userPresenter.prepareFailView("New password cannot be empty");
        }
        else if ("".equals(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username cannot be empty");
        }
        else if (signupInputData.getUsername().contains(",")) {
            userPresenter.prepareFailView("Username cannot contain comma");
        }
        else if (signupInputData.getPassword().contains(",")) {
            userPresenter.prepareFailView("Password cannot contain comma");
        }
        else {
            final User user = userFactory.create(signupInputData.getUsername(),
                                                 signupInputData.getPassword());
            userDataAccessObject.save(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getName());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
