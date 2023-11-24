package interface_adapter.add_email;

import use_case.add_email.AddEmailInputBoundary;
import use_case.add_email.AddEmailInputData;

/**
 * The {@code AddEmailController} class is a controller in the context of the Model-View-Controller (MVC) pattern.
 * It handles incoming requests related to adding email details to a project, and passes those requests
 * to the appropriate use case interactor.
 */
public class AddEmailController {

    private final AddEmailInputBoundary addProjectUseCaseInteractor;

    /**
     * Constructs an {@code AddEmailController} with the specified use case interactor.
     *
     * @param addProjectUseCaseInteractor The use case interactor that this controller will delegate to
     *                                    when handling requests to add email details to a project
     */
    public AddEmailController(AddEmailInputBoundary addProjectUseCaseInteractor) {
        this.addProjectUseCaseInteractor = addProjectUseCaseInteractor;
    }

    /**
     * Handles a request to add email details to a project. It creates an instance of {@code AddEmailInputData}
     * and passes it to the use case interactor for further processing.
     *
     * @param projectName The name of the project to which the email will be added
     * @param email The email address to be added to the project
     */
    public void addProjectDetails(String projectName, String email) {
        AddEmailInputData addEmailInputData = new AddEmailInputData(projectName, email);
        addProjectUseCaseInteractor.updateProjectDetails(addEmailInputData);
    }
}
