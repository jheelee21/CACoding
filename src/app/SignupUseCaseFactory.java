package app;

import interface_adapter.clear_users.ClearController;
import interface_adapter.clear_users.ClearPresenter;
import interface_adapter.clear_users.ClearViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.clear_users.ClearInputBoundary;
import use_case.clear_users.ClearInteractor;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
            SignupViewModel signupViewModel, SignupUserDataAccessInterface signupUserDataAccessObject,
            ClearViewModel clearViewModel, ClearUserDataAccessInterface clearUserDataAccessInterface) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, signupUserDataAccessObject);
            ClearController clearController = createUserClearUseCase(viewManagerModel, clearViewModel, signupViewModel, clearUserDataAccessInterface);
            return new SignupView(signupController, signupViewModel, clearController, clearViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

    private static ClearController createUserClearUseCase(ViewManagerModel viewManagerModel, ClearViewModel clearViewModel, SignupViewModel signupViewModel, ClearUserDataAccessInterface clearUserDataAccessInterface) throws IOException {
        ClearOutputBoundary clearOutputBoundary = new ClearPresenter(viewManagerModel, clearViewModel, signupViewModel);

        ClearInputBoundary userClearInteractor = new ClearInteractor(clearUserDataAccessInterface, clearOutputBoundary);

        return new ClearController(userClearInteractor);
    }
}
