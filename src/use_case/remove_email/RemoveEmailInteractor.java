package use_case.remove_email;

import use_case.remove_email.RemoveEmailDataAccessInterface;
import use_case.remove_email.RemoveEmailInputBoundary;
import use_case.remove_email.RemoveEmailInputData;
import use_case.remove_email.RemoveEmailOutputBoundary;

/**
 * The {@code RemoveEmailInteractor} class implements the {@code RemoveEmailInputBoundary} interface.
 * It serves as an interactor in the use case of removing an email from a project, coordinating the data access
 * and the presentation logic.
 */
public class RemoveEmailInteractor implements RemoveEmailInputBoundary {
    final RemoveEmailDataAccessInterface removeEmailDataAccessObject;
    final RemoveEmailOutputBoundary removeEmailPresenter;

    /**
     * Constructs an instance of {@code RemoveEmailInteractor}.
     *
     * This constructor initializes the {@code RemoveEmailInteractor} with a data access object
     * and an output boundary object. These objects are used for removing member emails from projects
     * and handling the presentation logic after the operation, respectively.
     *
     * @param projectDataAccessInterface the data access object used for removing member emails
     * @param removeEmailOutputBoundary the output boundary object used for presentation logic
     */
    public RemoveEmailInteractor(RemoveEmailDataAccessInterface projectDataAccessInterface,
                              RemoveEmailOutputBoundary removeEmailOutputBoundary) {
        this.removeEmailDataAccessObject = projectDataAccessInterface;
        this.removeEmailPresenter = removeEmailOutputBoundary;
    }

    /**
     * Processes the request to remove an email from a project.
     *
     * This method takes an instance of {@code RemoveEmailInputData} which contains the project name
     * and the email to be removed, updates the project details using the data access object,
     * and then prepares the success view using the presenter.
     *
     * @param removeEmailInputData the data transfer object containing information about the project and the email to be removed
     */
    @Override
    public void updateProjectDetails(RemoveEmailInputData removeEmailInputData) {
        String projectName = removeEmailInputData.getProjectName();
        String email = removeEmailInputData.getEmail();
        removeEmailDataAccessObject.removeMemberFromProject(projectName, email);
        removeEmailPresenter.prepareSuccessView();
    }
}