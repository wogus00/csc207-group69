package entity;

import java.util.ArrayList;
/**
 * The {@code CommonProject} class represents a common project entity.
 * It implements the {@code Project} interface and provides methods to
 * manage project details such as project name, leader's email, and member emails.
 */

public class CommonProject implements Project {
    private final String projectName;
    private String leaderEmail;
    private final ArrayList<String> memberEmails;

    /**
     * Constructs a new CommonProject with the specified project name, leader's email,
     * and list of member emails.
     *
     * @param projectName   the name of the project
     * @param leaderEmail   the email address of the project leader
     * @param memberEmails  the list of email addresses of project members
     */

    public CommonProject(String projectName, String leaderEmail, ArrayList<String> memberEmails) {
        this.projectName = projectName;
        this.leaderEmail = leaderEmail;
        this.memberEmails = memberEmails;
    }

    /**
     * Retrieves the project name.
     *
     * @return the name of the project
     */
    @Override
    public String getProjectName(){
        return projectName;
    }

    /**
     * Retrieves the email of the project leader.
     *
     * @return the email address of the project leader
     */
    @Override
    public String getLeaderEmail() {
        return leaderEmail;
    }

    /**
     * Sets the email of the project leader.
     *
     * @param email the new email address of the project leader
     */
    @Override
    public void setLeaderEmail(String email){
        leaderEmail = email;
    }

    /**
     * Retrieves the list of member emails.
     *
     * @return an ArrayList containing the email addresses of project members
     */
    @Override
    public ArrayList<String> getMemberEmails() {
        return memberEmails;
    }

    /**
     * Adds a new member email to the project.
     *
     * @param email the email address to be added to the project members list
     */
    @Override
    public void addMemberEmails(String email){
        memberEmails.add(email);
    }

    /**
     * Removes a member email from the project.
     *
     * @param email the email address to be removed from the project members list
     */
    @Override
    public void removeMemberEmails(String email){
        memberEmails.remove(email);
    }
}
