package entity;

import java.util.ArrayList;

/**
 * The {@code CommonProjectFactory} class implements the {@code ProjectFactory} interface.
 * It provides a method to create a new instance of {@code CommonProject}.
 */
public class CommonProjectFactory implements ProjectFactory {

    /**
     * Creates and returns a new {@code CommonProject} instance.
     *
     * This method takes the project name, leader's email, and a list of member emails as parameters
     * and creates a new instance of {@code CommonProject} with these details.
     *
     * @param projectName   the name of the project
     * @param leaderEmail   the email address of the project leader
     * @param memberEmails  the list of email addresses of project members
     * @return              a new instance of {@code CommonProject}
     */
    @Override
    public Project create(String projectName, String leaderEmail, ArrayList<String> memberEmails) {
         return new CommonProject(projectName, leaderEmail, memberEmails);
    }
}
