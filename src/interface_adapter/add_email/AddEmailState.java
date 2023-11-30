package interface_adapter.add_email;

import interface_adapter.create_announcement.CreateAnnouncementState;

/**
 * The {@code AddEmailState} class represents the state of the view model for the add email process.
 * It holds information about the current state, specifically any error messages that might occur
 * during the email addition process.
 */
public class AddEmailState {
    private String error;

    private String projectName = "";

    /**
     * Constructs a copy of an existing {@code AddEmailState}.
     * This constructor is useful for creating a new state based on the current state,
     * which is helpful in situations where an immutable state is desired.
     *
     * @param copy The {@code AddEmailState} object to copy from
     */
    public AddEmailState(AddEmailState copy) {
        this.projectName = copy.projectName;
        this.error = copy.error;
    }
    /**
     * Constructs a new {@code AddEmailState} with no errors.
     * This default constructor initializes the state with no error message, representing
     * the initial state of the add email process.
     */
    public AddEmailState() {}

    /**
     * Sets the error message for the email addition process.
     * This method updates the state with an error message, which can be used to inform
     * the user of any issues that occurred during the process.
     *
     * @param error The error message to be set in the state
     */
    public void setError(String error) {
        this.error = error;
    }
    public String error(){return error;}

    public void setProjectName(String projectName) {this.projectName = projectName;}

    public String getProjectName() {return this.projectName;}

}
