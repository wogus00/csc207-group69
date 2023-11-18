package interface_adapter.login;

public class LoginState {
    private String projectName = "";
    private String loginError = null;
    private String userEmail = "";


    public LoginState(LoginState copy) {
        projectName = copy.projectName;
        loginError = copy.loginError;
        userEmail = copy.userEmail;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {
    }

    public String getProjectName() {
        return projectName;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getUserEmail() {
        return userEmail;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setLoginError(String NoMatchError) {
        this.loginError = NoMatchError;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
