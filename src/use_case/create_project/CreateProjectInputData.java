package use_case.create_project;

import java.util.ArrayList;

public class CreateProjectInputData {
    final private String projectName;

    final private String leaderEmail;
    final private ArrayList<String> memberEmails;

    public CreateProjectInputData(String projectName, String leaderEmail, ArrayList<String> memberEmails) {
        this.projectName = projectName;
        this.leaderEmail = leaderEmail;
        this.memberEmails = memberEmails;
    }

    public String getProjectName() {
        return this.projectName;
    }
    public ArrayList<String> getMemberEmails() {
        return this.memberEmails;
    }
    public String getLeaderEmail() {
        return this.leaderEmail;
    }
}
