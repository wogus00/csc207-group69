package use_case.modify_task;

public interface ModifyTaskOutputBoundary {
    void prepareFailView(String error);

    void prepareSuccessView(ModifyTaskOutputData modifyTaskOutputData);
}