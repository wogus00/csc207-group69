package use_case.login;

public class LoginOutputData {

    private final String projectName;
    private final String userEmail;
    private boolean useCaseFailed;

    public LoginOutputData(String projectName, String userEmail, boolean useCaseFailed) {
        this.projectName = projectName;
        this.userEmail = userEmail;
        this.useCaseFailed = useCaseFailed;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUserEmail(){
        return userEmail;
    }


}
