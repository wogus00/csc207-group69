package use_case.set_leader;

/**
 * The {@code SetLeaderDataAccessInterface} interface defines the contract for setting a new leader for a project.
 * It is part of the data access layer, which interacts with the data source or database.
 */
public interface SetLeaderDataAccessInterface {

    /**
     * Updates the project data in the data source to set a new leader.
     *
     * This method is responsible for finding the project by its name in the data source and then updating
     * its leader to the specified leader. This is a key part of managing project leadership within the system.
     *
     * @param projectName the name of the project for which the leader is to be set
     * @param leader_name the name of the new leader to be set for the project
     */
    void SetLeaderToNewLeader(String projectName, String leader_name);
}
