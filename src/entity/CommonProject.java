package entity;

import java.util.ArrayList;

public class CommonProject implements Project {
    private final String projectName;
    private String leaderEmail;
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
    public void setLeaderEmail(String email){
        leaderEmail = email;
    }

    @Override
    public ArrayList<String> getMemberEmails() {
        return memberEmails;
    }

    @Override
    public void addMemberEmails(String email){
        memberEmails.add(email);
    }

    @Override
    public void removeMemberEmails(String email){
        memberEmails.remove(email);
    }
}
