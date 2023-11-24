package use_case.login;

/**
 * Input data class for the login use case
 * This class encapsulates the project name that the user wants to log in and the email of the user logging in
 */
public class LoginInputData {

    final private String projectName;
    final private String userEmail;
    /**
     * Constructs a new LoginInputData class with the specific inputs
     *
     * @param projectName String representing the project name that the user wants to log in
     * @param userEmail String representing the user email of the user logging in
     */
    public LoginInputData(String projectName, String userEmail) {
        this.projectName = projectName;
        this.userEmail = userEmail;
    }
    /**
     * Retrieves the project name inputted
     *
     * @return String representing the inputted project name
     */
    String getProjectName() {
        return projectName;
    }

    /**
     * Retrieves the user email inputted
     *
     * @return String representing the user email inputted
     */
    String getUserEmail() {
        return userEmail;
    }
}
