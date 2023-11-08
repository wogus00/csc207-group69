package use_case.login;

public class LoginInputData {

    final private String projectName;
    final private String userEmail;

    public LoginInputData(String projectName, String userEmail) {
        this.projectName = projectName;
        this.userEmail = userEmail;
    }

    String getProjectName() {
        return projectName;
    }

    String getUserEmail() {
        return userEmail;
    }
}
