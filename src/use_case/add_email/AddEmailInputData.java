package use_case.add_email;

/**
 * The {@code AddEmailInputData} class represents a data transfer object (DTO) used
 * in the process of adding an email to a project. It encapsulates the necessary information
 * required for this operation, such as the project's name and the email to be added.
 */
public class AddEmailInputData {
    private String projectName;
    private String email;

    /**
     * Constructs a new instance of {@code AddEmailInputData} with specified project name and email.
     *
     * @param projectName The name of the project to which the email will be added
     * @param email The email address to be added to the project
     */
    public AddEmailInputData(String projectName, String email) {
        this.projectName = projectName;
        this.email = email;
    }

    /**
     * Retrieves the name of the project.
     *
     * @return The name of the project associated with this data
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Retrieves the email address to be added to the project.
     *
     * @return The email address associated with this data
     */
    public String getEmail() {
        return email;
    }
}
