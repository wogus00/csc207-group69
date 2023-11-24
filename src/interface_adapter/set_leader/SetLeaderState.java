package interface_adapter.set_leader;

import interface_adapter.set_leader.SetLeaderState;

/**
 * The {@code SetLeaderState} class represents the state of the view model for the set leader process.
 * It holds information about the current state, specifically any error messages that might occur
 * during the process of setting a new leader for a project.
 */
public class SetLeaderState {
    private String error;

    /**
     * Constructs a copy of an existing {@code SetLeaderState}.
     * This constructor is useful for creating a new state based on the current state,
     * which is helpful in situations where an immutable state is desired.
     *
     * @param copy The {@code SetLeaderState} object to copy from
     */
    public SetLeaderState(SetLeaderState copy) {
        this.error = copy.error;
    }

    /**
     * Constructs a new {@code SetLeaderState} with no initial errors.
     * This default constructor initializes the state with no error message, representing
     * the initial state of the set leader process.
     */
    public SetLeaderState() {}

    /**
     * Sets the error message for the set leader process.
     * This method updates the state with an error message, which can be used to inform
     * the user of any issues that occurred during the process.
     *
     * @param error The error message to be set in the state
     */
    public void setError(String error) {
        this.error = error;
    }
    public String error(){return error;}
}
