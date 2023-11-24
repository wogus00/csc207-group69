package interface_adapter.remove_email;

import interface_adapter.add_email.AddEmailState;

/**
 * The {@code RemoveEmailState} class represents the state of the view model for the remove email process.
 * It holds information about the current state, specifically any error messages that might occur
 * during the email removal process.
 */
public class RemoveEmailState {
    private String error;

    /**
     * Constructs a copy of an existing {@code RemoveEmailState}.
     * This constructor is useful for creating a new state based on the current state,
     * which is helpful in situations where an immutable state is desired.
     *
     * @param copy The {@code RemoveEmailState} object to copy from
     */
    public RemoveEmailState(RemoveEmailState copy) {
        this.error = copy.error;
    }

    /**
     * Constructs a new {@code RemoveEmailState} with no errors.
     * This default constructor initializes the state with no error message, representing
     * the initial state of the remove email process.
     */
    public RemoveEmailState() {}

    /**
     * Sets the error message for the email removal process.
     * This method updates the state with an error message, which can be used to inform
     * the user of any issues that occurred during the process.
     *
     * @param error The error message to be set in the state
     */
    public void setError(String error) {
        this.error = error;
    }
}
