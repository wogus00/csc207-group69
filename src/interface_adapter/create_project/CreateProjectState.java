package interface_adapter.create_project;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateProjectState {
    private String projectName = "";
    private String projectNameError = null;
    private String leaderEmail = "";
    private ArrayList<String> memberEmail = new ArrayList<>();

    public CreateProjectState(CreateProjectState copy) {
        projectName = copy.projectName;
        projectNameError = copy.projectNameError;
        leaderEmail = copy.leaderEmail;
        memberEmail = copy.memberEmail;
    }

    public CreateProjectState() {}

    public String getProjectName() {
        return projectName;
    }

    public String getProjectNameError() {
        return projectNameError;
    }

    public String getLeaderEmail() {
        return leaderEmail;
    }

    public ArrayList<String> getMemberEmail() {
        return memberEmail;
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

    public void setMemberEmail(String memberEmailString) {
        String[] parts = memberEmailString.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].strip();
        }
        this.memberEmail = new ArrayList<>(Arrays.asList(parts));
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
