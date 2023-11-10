package use_case.add_email;

public class AddEmailInputData {
    private String projectName;
    private String email;

    public AddEmailInputData(String projectName, String email) {
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
