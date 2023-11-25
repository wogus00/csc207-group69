package use_case.complete_task;

public interface CompleteTaskOutputBoundary {
    void prepareFailView(String errorMessage);

    void prepareSuccessView(CompleteTaskOutputData completeTaskOutputData);
}
