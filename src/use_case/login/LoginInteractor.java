package use_case.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Interactor class for the login use case
 * Responsible for executing the business logic associated with logging a user into a project.
 * Interacts with the FirebaseAccessObject through data access interface to see whether the project exist
 * and whether the user is a valid member of the project
 * Interacts with the LoginPresenter through the output boundary to update the view based on the result of
 * login
 */

public class LoginInteractor implements LoginInputBoundary {
    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    /**
     * Constructs a new LoginInteractor class with the specific interfaces
     *
     * @param userDataAccessInterface Data access interface responsible for communication with Firebase API
     * @param loginOutputBoundary Output boundary interface responsible for presenting the result of the login action
     */
    public LoginInteractor(LoginDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    /**
     * Executes the login action
     * It determines whether the user with the user email can log in to the project with the project name
     * It sets up the success view in login presenter if the project exists and the user email is a member
     * in the project (requirements for success).
     * If any requirement fails, it sets up the fail view with error message
     *
     *  @param loginInputData Contains information about the name of project we are logging into, and the
     *                        user email for the user who is logging in. These are required information to
     *                        determine whether the user can log in to the project
     */
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