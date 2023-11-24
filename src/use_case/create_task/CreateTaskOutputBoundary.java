package use_case.create_task;

public interface CreateTaskOutputBoundary {
    void prepareFailView(String error);

    void prepareSuccessView(CreateTaskOutputData createTaskOutputData);
}