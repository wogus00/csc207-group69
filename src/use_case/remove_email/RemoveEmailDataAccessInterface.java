package use_case.remove_email;

/**
 * The {@code RemoveEmailDataAccessInterface} interface defines the contract for removing a member's email
 * from a project. It is part of the data access layer, which interacts with the data source or database.
 */
public interface RemoveEmailDataAccessInterface {

    /**
     * Removes a member's email from a project identified by its name.
     *
     * This method is responsible for updating the project data in the data source
     * to remove a member's email. The method will locate the project by its name
     * and then remove the specified email from the project's member list.
     *
     * @param projectName The name of the project from which the member email should be removed
     * @param email The email address of the member to be removed from the project
     */
    void removeMemberFromProject(String projectName, String email);
}
