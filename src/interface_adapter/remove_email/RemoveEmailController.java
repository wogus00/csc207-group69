package interface_adapter.remove_email;

import use_case.remove_email.RemoveEmailInputBoundary;
import use_case.remove_email.RemoveEmailInputData;

/**
 * The {@code RemoveEmailController} class acts as a controller for handling removal of email details from a project.
 * It receives input from the UI, creates a data transfer object, and passes it to the use case interactor for further processing.
 */
public class RemoveEmailController {
    private final RemoveEmailInputBoundary removeProjectUseCaseInteractor;

    /**
     * Constructs a {@code RemoveEmailController} with a given {@code RemoveEmailInputBoundary}.
     *
     * @param removeProjectUseCaseInteractor The use case interactor that this controller will delegate to
     *                                       when removing email details from a project.
     */
    public RemoveEmailController(RemoveEmailInputBoundary removeProjectUseCaseInteractor) {
        this.removeProjectUseCaseInteractor = removeProjectUseCaseInteractor;
    }

    /**
     * Initiates the process to remove email details from a project.
     * It creates an instance of {@code RemoveEmailInputData} with the provided project name and email,
     * then calls the use case interactor to perform the operation.
     *
     * @param projectName The name of the project from which the email should be removed.
     * @param email       The email address to be removed from the project.
     */
    public void removeProjectDetails(String projectName, String email) {
        RemoveEmailInputData removeEmailInputData = new RemoveEmailInputData(projectName, email);
        removeProjectUseCaseInteractor.updateProjectDetails(removeEmailInputData);
    }
}
