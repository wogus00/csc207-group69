package use_case.set_leader;

public class SetLeaderInputData {
    private String projectName;
    private String new_leader;

    public SetLeaderInputData(String projectName, String new_leader) {
        this.projectName = projectName;
        this.new_leader = new_leader;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getEmail() {
        return new_leader;
    }
}
