package interface_adapter.create_task;

import interface_adapter.ViewManagerModel;
import use_case.create_task.CreateTaskOutputBoundary;
import use_case.create_task.CreateTaskOutputData;

public class CreateTaskPresenter implements CreateTaskOutputBoundary {
    private final CreateTaskViewModel createTaskViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateTaskPresenter(ViewManagerModel viewManagerModel, CreateTaskViewModel createTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createTaskViewModel = createTaskViewModel;
    }
    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void prepareSuccessView(CreateTaskOutputData createTaskOutputData) {
    }
}