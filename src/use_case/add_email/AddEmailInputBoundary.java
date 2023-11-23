package use_case.add_email;

/**
 * The {@code AddEmailInputBoundary} interface defines the contract for an input boundary
 * in the use case of adding an email to a project. It is part of the application's use case layer
 * and acts as an intermediary between the presentation/controller layer and the domain layer.
 */
public interface AddEmailInputBoundary {

    /**
     * Updates the project details with new email information provided in {@code AddEmailInputData}.
     *
     * This method is responsible for initiating the process of adding a new email to a project.
     * It takes an instance of {@code AddEmailInputData} which contains all necessary information
     * about the project and the email to be added, and processes this data to update the project details.
     *
     * @param addEmailInputData the data transfer object containing information about the project and the email to be added
     */
    void updateProjectDetails(AddEmailInputData addEmailInputData);
}
