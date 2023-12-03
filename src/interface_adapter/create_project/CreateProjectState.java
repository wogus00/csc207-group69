package interface_adapter.create_project;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * State class for create project use case.
 * This class stores data about the project and any errors encountered in its creation.
 */
public class CreateProjectState {
    private String projectName = "";
    private String projectNameError = null;
    private String leaderEmail = "";
    private ArrayList<String> memberEmail = new ArrayList<>();

    /**
     * Constructs a new state by copying data from another state instance.
     *
     * @param copy the state instance to copy the data from
     */
    public CreateProjectState(CreateProjectState copy) {
        projectName = copy.projectName;
        projectNameError = copy.projectNameError;
        leaderEmail = copy.leaderEmail;
        memberEmail = copy.memberEmail;
    }
    /**
     * Default constructor for creating an initial blank state.
     */
    public CreateProjectState() {}

    /**
     * Getter method that returns the project name.
     * @return String representing the project title.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Getter method that returns an error when creating a project.
     * @return String indicating that an error occurred during project creation.
     */
    public String getProjectNameError() {
        return projectNameError;
    }

    /**
     * Getter method that returns the email addresses of project participants.
     * @return String containing email address of project leader.
     */
    public String getLeaderEmail() {
        return leaderEmail;
    }

    /**
     * Getter method that returns the email addresses of project participants.
     * @return ArrayList<String> containing email addresses of all project participants.
     */
    public ArrayList<String> getMemberEmail() {
        return memberEmail;
    }

    /**
     * Setter method to set meetingName as the title of the project to be created.
     * @param projectName String of the project title
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Setter method to sets an error when creating a project.
     * @param projectNameError String indicating that an error occurred during project creation.
     */
    public void setProjectNameError(String projectNameError) {
        this.projectNameError = projectNameError;
    }

    /**
     * Setter method to sets the leader email for the project.
     * @param leaderEmail String containing email address of project leader.
     */
    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }

    /**
     * Setter method to update participants in the project.
     * @param memberEmailString String containing all participant email addresses, separated by a comma.
     */
    public void setMemberEmail(String memberEmailString) {
        String[] parts = memberEmailString.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].strip();
        }
        this.memberEmail = new ArrayList<>(Arrays.asList(parts));
    }

    /**
     * Method to return project details in a sentence.
     * @return String is a sentence including the details of the meeting.
     */
    @Override
    public String toString() {
        return "CreateProjectState{" +
                "project name='" + projectName + '\'' +
                ", leader email='" + leaderEmail + '\'' +
                ", member email" + memberEmail + '\'' +
                '}';
    }
}
