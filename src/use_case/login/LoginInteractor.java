package use_case.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            loginPresenter.prepareFailView("Project does not exist.");
        } else {
            ArrayList<String> memberEmails = userDataAccessObject.getProjectInfo(projectName).getMemberEmails();
            String leaderEmail = userDataAccessObject.getProjectInfo(projectName).getLeaderEmail();
            if (!userEmail.equals(leaderEmail) && !memberEmails.contains(userEmail)) {
                loginPresenter.prepareFailView("User email " + userEmail + " does not exist for the project " + projectName + ".");
            } else {
                ArrayList<String> taskList = userDataAccessObject.getInfoList(projectName, "taskInfo");
                ArrayList<String> meetingList = userDataAccessObject.getInfoList(projectName, "meetingInfo");
                ArrayList<String> announcements = userDataAccessObject.getInfoList(projectName, "announcementInfo");
                LoginOutputData loginOutputData = new LoginOutputData(projectName, userEmail, leaderEmail, memberEmails, taskList, meetingList, announcements, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}