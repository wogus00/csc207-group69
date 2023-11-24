package use_case.remove_email;

/**
 * The {@code RemoveEmailInputData} class represents a data transfer object (DTO) used
 * in the process of removing an email from a project. It encapsulates the necessary information
 * required for this operation, such as the project's name and the email to be removed.
 */
public class RemoveEmailInputData {
    private String projectName;
    private String email;

    /**
     * Constructs a new instance of {@code RemoveEmailInputData} with a specified project name and email.
     *
     * @param projectName The name of the project from which the email will be removed
     * @param email The email address to be removed from the project
     */
    public RemoveEmailInputData(String projectName, String email) {
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
     * Retrieves the email address to be removed from the project.
     *
     * @return The email address associated with this data
     */
    public String getEmail() {
        return email;
    }
}
