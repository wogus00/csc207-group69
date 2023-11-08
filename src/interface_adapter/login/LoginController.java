package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;


public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String projectName, String userEmail) {
        LoginInputData loginInputData = new LoginInputData(
                projectName, userEmail);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
