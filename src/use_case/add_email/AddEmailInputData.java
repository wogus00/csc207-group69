package use_case.add_email;

public class AddEmailInputData {
    private String projectName;
    private String emailToAdd;

    public AddEmailInputData(String projectName, String emailToAdd) {
        this.projectName = projectName;
        this.emailToAdd = emailToAdd;
    }
}
