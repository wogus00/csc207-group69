package entity;

import java.util.ArrayList;

/**
 * The {@code ProjectFactory} interface defines the blueprint for a factory class
 * that creates instances of {@code Project}. It includes a method to create a new
 * project with specified details.
 */
public interface ProjectFactory {

    /**
     * Creates and returns a new {@code Project} instance.
     *
     * This method takes the project name, leader's email, and a list of member emails
     * as parameters and returns a new instance of a class that implements the {@code Project} interface
     * with these details.
     *
     * @param projectName   the name of the project
     * @param leaderEmail   the email address of the project leader
     * @param memberEmails  the list of email addresses of project members
     * @return              a new instance of a class that implements the {@code Project} interface
     */
    Project create(String projectName, String leaderEmail, ArrayList<String> memberEmails);
}
