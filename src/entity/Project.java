package entity;

import java.util.ArrayList;

/**
 * The {@code Project} interface defines the structure of a project entity.
 * It includes methods for retrieving and modifying project details such as
 * project name, leader's email, and member emails.
 */
public interface Project {

    /**
     * Retrieves the name of the project.
     *
     * @return the name of the project
     */
    String getProjectName();

    /**
     * Retrieves the email of the project leader.
     *
     * @return the email address of the project leader
     */
    String getLeaderEmail();

    /**
     * Sets or updates the email address of the project leader.
     *
     * @param email the new email address of the project leader
     */
    void setLeaderEmail(String email);

    /**
     * Retrieves the list of member emails associated with the project.
     *
     * @return an ArrayList containing the email addresses of project members
     */
    ArrayList<String> getMemberEmails();

    /**
     * Adds a new member's email to the project.
     *
     * @param email the email address to be added to the project members list
     */
    void addMemberEmails(String email);

    /**
     * Removes a member's email from the project.
     *
     * @param email the email address to be removed from the project members list
     */
    void removeMemberEmails(String email);
}
