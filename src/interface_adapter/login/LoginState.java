package interface_adapter.login;

public class LoginState {
    private String projectName = "";
    private String NoMatchError = null;
    private String userEmail = "";


    public LoginState(LoginState copy) {
        projectName = copy.projectName;
        NoMatchError = copy.NoMatchError;
        userEmail = copy.userEmail;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {
    }

    public String getProjectName() {
        return projectName;
    }

    public String getNoMatchError() {
        return NoMatchError;
    }

    public String getUserEmail() {
        return userEmail;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setNoMatchError(String NoMatchError) {
        this.NoMatchError = NoMatchError;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
