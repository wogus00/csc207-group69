package interface_adapter.complete_task;

import interface_adapter.ViewManagerModel;
import use_case.complete_task.CompleteTaskOutputBoundary;
import use_case.complete_task.CompleteTaskOutputData;

public class CompleteTaskPresenter implements CompleteTaskOutputBoundary {
    private final CompleteTaskViewModel completeTaskViewModel;
    private ViewManagerModel viewManagerModel;

    public CompleteTaskPresenter(ViewManagerModel viewManagerModel, CompleteTaskViewModel completeTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.completeTaskViewModel = completeTaskViewModel;
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void prepareSuccessView(CompleteTaskOutputData completeTaskOutputData) {

    }
}
