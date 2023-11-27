package use_case.remove_email;

/**
 * The {@code RemoveEmailOutputBoundary} interface defines the contract for an output boundary
 * in the use case of removing an email from a project. It specifies methods for preparing different
 * views based on the success or failure of the operation.
 */
public interface RemoveEmailOutputBoundary {

    /**
     * Prepares and presents a success view for the remove email operation.
     *
     * This method is called when removing an email from a project has been completed successfully.
     * It should handle the logic for displaying a success message or view to the user.
     */
    void prepareSuccessView();

    /**
     * Prepares and presents a failure view with a specified error message.
     *
     * This method is called when removing an email from a project has failed.
     * It should handle the logic for displaying an error message or view to the user,
     * providing feedback on what went wrong.
     *
     * @param error The error message to be displayed in the failure view
     */
    void prepareFailView(String error);
}
