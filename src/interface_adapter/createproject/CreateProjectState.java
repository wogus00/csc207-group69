package interface_adapter.createproject;

public class CreateProjectState {
    private String projectName = "";
    private String projectNameError = null;
    private String leaderEmail = "";
    private String leaderEmailError = null;
    private String memberEmail = "";
    private String memberEmailError = null;

    public CreateProjectState(CreateProjectState copy) {
        projectName = copy.projectName;
        projectNameError = copy.projectNameError;
        leaderEmail = copy.leaderEmail;
        leaderEmailError = copy.leaderEmailError;
        memberEmail = copy.memberEmail;
        memberEmailError = copy.memberEmailError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
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

    public String getMemberEmail() {
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

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setMemberEmailError(String memberEmailError) {
        this.memberEmailError = memberEmailError;
    }

    @Override
    public String toString() {
        return "CreateProjectState{" +
                "projectName='" + projectName + '\'' +
                ", leaderEmail='" + leaderEmail + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}
