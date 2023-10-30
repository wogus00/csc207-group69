package entity;

import java.util.ArrayList;

public class CommonProject implements Project {
    private final String projectName;
    private final String leaderEmail;
    private final ArrayList<String> memberEmails;

    public CommonProject(String projectName, String leaderEmail, ArrayList<String> memberEmails) {
        this.projectName = projectName;
        this.leaderEmail = leaderEmail;
        this.memberEmails = memberEmails;
    }

    @Override
    public String getProjectName(){
        return projectName;
    }

    @Override
    public String getLeaderEmail() {
        return leaderEmail;
    }

    @Override
    public ArrayList<String> getMemberEmails() {
        return memberEmails;
    }
}
