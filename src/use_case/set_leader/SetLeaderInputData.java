package use_case.set_leader;

/**
 * The {@code SetLeaderInputData} class represents a data transfer object (DTO) used
 * in the process of setting a new leader for a project. It encapsulates the necessary information
 * required for this operation, such as the project's name and the new leader's name.
 */
public class SetLeaderInputData {
    private String projectName;
    private String new_leader;

    /**
     * Constructs a new instance of {@code SetLeaderInputData} with a specified project name and new leader's name.
     *
     * @param projectName the name of the project for which the leader is to be set
     * @param new_leader the name of the new leader to be set for the project
     */
    public SetLeaderInputData(String projectName, String new_leader) {
        this.projectName = projectName;
        this.new_leader = new_leader;
    }

    /**
     * Retrieves the name of the project.
     *
     * @return the name of the project associated with this data
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Retrieves the name of the new leader to be set for the project.
     *
     * @return the name of the new leader associated with this data
     */
    public String getEmail() {
        return new_leader;
    }
}
