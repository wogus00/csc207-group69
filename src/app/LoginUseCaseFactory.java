package app;

import entity.CommonProjectFactory;
import entity.ProjectFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView createLoginView(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            MainPageViewModel mainPageViewModel,
            LoginDataAccessInterface loginDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, mainPageViewModel, loginDataAccessObject);
            return new LoginView(loginViewModel, loginController,viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid Login");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            MainPageViewModel mainPageViewModel,
            LoginDataAccessInterface loginDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, mainPageViewModel, loginViewModel);

        ProjectFactory projectFactory = new CommonProjectFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                loginDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
