package use_case.remove_email;

public class RemoveEmailInputData {
    private String projectName;
    private String email;

    public RemoveEmailInputData(String projectName, String email) {
        this.projectName = projectName;
        this.email = email;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getEmail() {
        return email;
    }
}
