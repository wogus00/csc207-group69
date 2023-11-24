package use_case.remove_email;

import use_case.add_email.AddEmailInputData;

/**
 * The {@code RemoveEmailInputBoundary} interface defines the contract for an input boundary
 * in the use case of removing an email from a project. It is part of the application's use case layer
 * and acts as an intermediary between the presentation/controller layer and the domain layer.
 */
public interface RemoveEmailInputBoundary {

    /**
     * Processes the request to remove an email from a project.
     *
     * This method takes an instance of {@code RemoveEmailInputData} which contains the project name
     * and the email to be removed, and initiates the process of updating the project details
     * to reflect the removal of this email.
     *
     * @param removeEmailInputData The data transfer object containing information about the project and the email to be removed
     */
    void updateProjectDetails(RemoveEmailInputData removeEmailInputData);
}
