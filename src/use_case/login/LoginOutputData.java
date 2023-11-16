package use_case.login;

import java.util.ArrayList;

public class LoginOutputData {

    private final String projectName;
    private final String userEmail;
    private final String leaderEmail;
    private final ArrayList<String> memberEmails;
    private boolean useCaseFailed;

    public LoginOutputData(String projectName, String userEmail, String leaderEmail, ArrayList<String> memberEmails, boolean useCaseFailed) {
        this.projectName = projectName;
        this.userEmail = userEmail;
        this.leaderEmail = leaderEmail;
        this.memberEmails = memberEmails;
        this.useCaseFailed = useCaseFailed;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getLeaderEmail() {return leaderEmail;}

    public ArrayList<String> getMemberEmails() {return memberEmails;}

}
