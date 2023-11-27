package use_case.create_project;

import java.util.ArrayList;

/**
 * Input Data class representing the data inputted by user for creating a project.
 * This class encapsulates the necessary information to create a project.
 */
public class CreateProjectInputData {
    final private String projectName;

    final private String leaderEmail;
    final private ArrayList<String> memberEmails;

    /**
     * Constructor method that creates new CreateProjectInputData with specified information needed when creating project.
     * @param projectName String that represents the name of the project to be created
     * @param leaderEmail String of email that represents the leader of this project.
     * @param memberEmails String of emails that represent the emails of invited users.
     */
    public CreateProjectInputData(String projectName, String leaderEmail, ArrayList<String> memberEmails) {
        this.projectName = projectName;
        this.leaderEmail = leaderEmail;
        this.memberEmails = memberEmails;
    }

    /**
     * Getter method to return name of the project from CreateProjectInputData.
     * @return String of project name.
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Getter method to return emails of the invited users from CreateProjectInputData.
     * @return ArrayList of String of invited users' emails.
     */
    public ArrayList<String> getMemberEmails() {
        return this.memberEmails;
    }

    /**
     * Getter method to return email of the leader from CreateProjectInputData.

     * @return String of leader's email.
     */
    public String getLeaderEmail() {
        return this.leaderEmail;
    }
}
