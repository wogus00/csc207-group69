package use_case.create_project;

public interface CreateProjectOutputData {
    void prepareSuccessView(CreateProjectOutputData project);

    void prepareFailView(String error);
}
