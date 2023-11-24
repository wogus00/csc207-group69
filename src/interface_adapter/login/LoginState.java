package interface_adapter.login;

/**
 * State class for the login use case
 * It stores input data related to the login action and errors messages for failure case of login.
 */
public class LoginState {
    private String projectName = "";
    private String loginError = null;
    private String userEmail = "";
    private boolean logout = false;

    /**
     * Creates a new LoginState as a copy of another LoginState.
     *
     * @param copy The LoginState instance to copy.
     */
    public LoginState(LoginState copy) {
        projectName = copy.projectName;
        loginError = copy.loginError;
        userEmail = copy.userEmail;
        logout = copy.logout;
    }

    /**
     * Constructs a default LoginState with initial values.
     */
    public LoginState() {
    }

    /**
     * Retrieves the project name.
     *
     * @return The name of the project.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Retrieves the login error message.
     *
     * @return The login error message, if any; otherwise, null.
     */
    public String getLoginError() {
        return loginError;
    }

    /**
     * Retrieves the user's email.
     *
     * @return The email of the user.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the name of the project.
     *
     * @param projectName The name to be set for the project.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Sets the login error message.
     *
     * @param LoginError The error message representing the reason for a failed login action
     */
    public void setLoginError(String LoginError) {
        this.loginError = LoginError;
    }

    /**
     * Sets the user's email.
     *
     * @param userEmail The email to be set for the user.
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Sets the logout state.
     *
     * @param logout The boolean state to be set for logout.
     */
    public void setLogout(boolean logout) {
        this.logout = logout;
    }

    /**
     * Checks if the project is logged out.
     *
     * @return True if logout is set, otherwise false.
     */
    public boolean isLogout() {
        return this.logout;
    }
}
