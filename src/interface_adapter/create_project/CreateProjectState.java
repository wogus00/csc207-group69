package interface_adapter.create_project;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateProjectState {
    private String projectName = "";
    private String projectNameError = null;
    private String leaderEmail = "";
    private String leaderEmailError = null;
    private ArrayList<String> memberEmail = new ArrayList<>();
    private String memberEmailError = null;

    public CreateProjectState(CreateProjectState copy) {
        projectName = copy.projectName;
        projectNameError = copy.projectNameError;
        leaderEmail = copy.leaderEmail;
        leaderEmailError = copy.leaderEmailError;
        memberEmail = copy.memberEmail;
        memberEmailError = copy.memberEmailError;
    }

    public CreateProjectState() {
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectNameError() {
        return projectNameError;
    }

    public String getLeaderEmail() {
        return leaderEmail;
    }

    public String getLeaderEmailError() {
        return leaderEmailError;
    }

    public ArrayList<String> getMemberEmail() {
        return memberEmail;
    }

    public String getMemberEmailError() {
        return memberEmailError;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectNameError(String projectNameError) {
        this.projectNameError = projectNameError;
    }

    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }

    public void setLeaderEmailError(String leaderEmailError) {
        this.leaderEmailError = leaderEmailError;
    }

    public void setMemberEmail(String memberEmailString) {
        String[] parts = memberEmailString.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        this.memberEmail = new ArrayList<>(Arrays.asList(parts));
    }

    public void setMemberEmailError(String memberEmailError) {
        this.memberEmailError = memberEmailError;
    }

    @Override
    public String toString() {
        return "CreateProjectState{" +
                "project name='" + projectName + '\'' +
                ", leader email='" + leaderEmail + '\'' +
                ", member email" + memberEmail + '\'' +
                '}';
    }
}
