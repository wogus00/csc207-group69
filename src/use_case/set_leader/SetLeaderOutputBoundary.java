package use_case.set_leader;

/**
 * The {@code SetLeaderOutputBoundary} interface defines the contract for the output boundary
 * of the set leader use case. It outlines methods for responding to the outcome of a request
 * to set a new project leader, allowing for appropriate user feedback.
 */
public interface SetLeaderOutputBoundary {

    /**
     * Prepares the view to indicate the successful setting of a new project leader.
     * This method should be invoked when the change of leadership has been completed
     * successfully and the user interface needs to reflect this success.
     */
    void prepareSuccessView();

    /**
     * Prepares the view to display an error message indicating that setting a new project leader
     * has failed.
     *
     * @param error A string describing the error that occurred while attempting to set a new project leader.
     *              This message should be user-friendly and possibly provide guidance on how to correct the issue.
     */
    void prepareFailView(String error);
}
