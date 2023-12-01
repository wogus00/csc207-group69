package use_case.add_email;

/**
 * The {@code AddEmailOutputBoundary} interface defines the contract for an output boundary
 * in the use case of adding an email to a project. It specifies methods for preparing different
 * views based on the success or failure of the operation.
 */
public interface AddEmailOutputBoundary {

    /**
     * Prepares and presents a success view for the add email operation.
     *
     * This method is called when adding an email to a project has been completed successfully.
     * It should handle the logic for displaying a success message or view to the user.
     */
    void prepareSuccessView(String newMember);

    /**
     * Prepares and presents a failure view with a specified error message.
     *
     * This method is called when adding an email to a project has failed.
     * It should handle the logic for displaying an error message or view to the user,
     * providing feedback on what went wrong.
     *
     * @param error The error message to be displayed in the failure view
     */
    void prepareFailView(String error);
}