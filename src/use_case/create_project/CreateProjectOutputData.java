package use_case.create_project;

import java.util.ArrayList;

public class CreateProjectOutputData {

    private final String projectName;
    private final String leaderEmail;
    private final ArrayList<String> memberEmails;
    private boolean useCaseFailed;

    public CreateProjectOutputData(String projectName, String leaderEmail, ArrayList<String> memberEmails, boolean useCaseFailed) {
        this.projectName = projectName;
        this.leaderEmail = leaderEmail;
        this.memberEmails = memberEmails;
        this.useCaseFailed = useCaseFailed;
    }

    public String getProjectName() {
        return projectName;
    }
    public String getLeaderEmail() {
        return leaderEmail;
    }
    public ArrayList<String> getMemberEmails(){
        return memberEmails;
    }
}
