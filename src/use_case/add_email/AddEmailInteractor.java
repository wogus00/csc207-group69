package use_case.add_email;

import entity.ProjectFactory;

import java.util.ArrayList;

/**
 * The {@code AddEmailInteractor} class implements the {@code AddEmailInputBoundary} interface.
 * It serves as an interactor in the use case of adding an email to a project, coordinating the data access
 * and the presentation logic.
 */
public class AddEmailInteractor implements AddEmailInputBoundary {
    final AddEmailDataAccessInterface addEmailDataAccessObject;
    final AddEmailOutputBoundary addEmailPresenter;

    /**
     * Constructs an instance of {@code AddEmailInteractor}.
     *
     * This constructor initializes the {@code AddEmailInteractor} with a data access object
     * and an output boundary object. These objects are used for adding member emails to projects
     * and handling the presentation logic after the operation, respectively.
     *
     * @param projectDataAccessInterface The data access object used for adding member emails
     * @param addEmailOutputBoundary The output boundary object used for presentation logic
     */
    public AddEmailInteractor(AddEmailDataAccessInterface projectDataAccessInterface,
                                   AddEmailOutputBoundary addEmailOutputBoundary) {
        this.addEmailDataAccessObject = projectDataAccessInterface;
        this.addEmailPresenter = addEmailOutputBoundary;
    }

    /**
     * Processes the request to add a new email to a project.
     *
     * This method takes an instance of {@code AddEmailInputData} which contains the project name
     * and the email to be added, updates the project details using the data access object,
     * and then prepares the success view using the presenter.
     *
     * @param addEmailInputData The data transfer object containing information about the project and the email to be added
     */
    @Override
    public void updateProjectDetails(AddEmailInputData addEmailInputData) {
        String projectName = addEmailInputData.getProjectName();
        String email = addEmailInputData.getEmail();
        addEmailDataAccessObject.addMemberToProject(projectName, email);
        addEmailPresenter.prepareSuccessView();
    }
}