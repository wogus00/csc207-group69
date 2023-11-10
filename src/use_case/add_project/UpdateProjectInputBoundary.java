package use_case.update_project;

public interface UpdateProjectInputBoundary {
    void updateProject(String projectName, String projectDescription);

    void updateProjectDetails(UpdateProjectInputData updateProjectInputData);
}
