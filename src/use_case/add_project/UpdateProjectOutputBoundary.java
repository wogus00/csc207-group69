package use_case.update_project;

public interface UpdateProjectOutputBoundary {
    void prepareSuccessView(UpdateProjectOutputData project);
    void prepareFailView(String error);
}