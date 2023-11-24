package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;

/**
 * Controller class responsible for handling user login requests.
 * Accepts input from the user and forwards the login process to the appropriate interactor
 */
public class LoginController {

    /**
     * Constructors a new LoginController with the specific interactor
     *
     * @param loginUseCaseInteractor Input boundary responsible for executing the business logic of a login action
     */
    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Execution method that packs the parameters into a input data object and send it to the responsible
     * interactor
     *
     * @param projectName the name of the project that the user intends to access
     * @param userEmail the email of the user performing the action
     */
    public void execute(String projectName, String userEmail) {
        LoginInputData loginInputData = new LoginInputData(
                projectName, userEmail);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
