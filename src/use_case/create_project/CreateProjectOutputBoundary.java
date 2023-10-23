package use_case.create_project;

public interface Create_ProjectOutputBoundary {
    void prepareSuccessView(Create_ProjectOutputData project);

    void prepareFailView(String error);
}