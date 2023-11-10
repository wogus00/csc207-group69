package use_case.update_project;

import java.util.ArrayList;

public class UpdateProjectOutputData {

    private final String projectName;
    private final String leaderEmail;
    private final ArrayList<String> memberEmails;
    private boolean useCaseFailed;

    public UpdateProjectOutputData(String projectName, String leaderEmail, ArrayList<String> memberEmails, boolean useCaseFailed) {
        this.projectName = projectName;
        this.leaderEmail = leaderEmail;
        this.memberEmails = memberEmails;
        this.useCaseFailed = useCaseFailed;
    }

    public String setLeaderEmail() {
        return leaderEmail;
    }
    public ArrayList<String> addMemberEmails(){
        return memberEmails;
    }
    public ArrayList<String> removeMemebrEmails();
}
