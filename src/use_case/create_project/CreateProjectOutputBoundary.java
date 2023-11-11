package use_case.create_project;

public interface CreateProjectOutputBoundary {
    void prepareSuccessView(CreateProjectOutputData project);
    void prepareFailView(String error);
}
