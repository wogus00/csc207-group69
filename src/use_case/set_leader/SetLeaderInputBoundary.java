package use_case.set_leader;

import use_case.set_leader.SetLeaderInputData;

/**
 * The {@code SetLeaderInputBoundary} interface defines the contract for an input boundary
 * in the use case of setting a new leader for a project. It is part of the application's use case layer
 * and acts as an intermediary between the presentation/controller layer and the domain layer.
 */
public interface SetLeaderInputBoundary {

    /**
     * Processes the request to set a new leader for a project.
     *
     * This method takes an instance of {@code SetLeaderInputData} which contains the project name
     * and the new leader's name, and initiates the process of updating the project details
     * to reflect the change in leadership.
     *
     * @param setLeaderInputData The data transfer object containing information about the project and the new leader to be set
     */
    void updateProjectDetails(SetLeaderInputData setLeaderInputData);
}
