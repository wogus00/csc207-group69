package use_case.add_email;

import entity.Project;

/**
 * The {@code AddEmailDataAccessInterface} interface defines the contract for adding a member's email
 * to a project. It is part of the data access layer, which interacts with the data source or database.
 */
public interface AddEmailDataAccessInterface {

    /**
     * Adds a new member's email to a project identified by its name.
     *
     * This method is responsible for updating the project data in the data source
     * to include a new member's email. The method will locate the project by its name
     * and then add the specified email to the project's member list.
     *
     * @param projectName the name of the project to which the member email should be added
     * @param email the email address of the new member to be added to the project
     */
    void addMemberToProject(String projectName, String email);
}