package use_case.login;

import java.util.ArrayList;

public class LoginInteractor implements LoginInputBoundary {
    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String projectName = loginInputData.getProjectName();
        String userEmail = loginInputData.getUserEmail();
        if (!userDataAccessObject.existsByName(projectName) ) {
            loginPresenter.prepareFailView(projectName + ": Project does not exist.");
        } else {
            ArrayList<String> memberEmails = userDataAccessObject.getProject(projectName).getMemberEmails();
            String leaderEmail = userDataAccessObject.getProject(projectName).getLeaderEmail();
            if (!userEmail.equals(leaderEmail) && !memberEmails.contains(userEmail)) {
                loginPresenter.prepareFailView("User email" + userEmail + " does not exist for the project" + projectName + ".");
            } else {
                LoginOutputData loginOutputData = new LoginOutputData(projectName, userEmail, leaderEmail, memberEmails, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}