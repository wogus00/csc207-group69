package interface_adapter.create_task;

import data_access.TaskListRetrieveStrategy;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_task.CreateTaskOutputBoundary;
import use_case.create_task.CreateTaskOutputData;
import view.CreateTaskView;
import view.MainPageView;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Presenter class for Create Task use case.
 * This class handles presentation logic adhering Clean Architecture by updating the view model appropriately based on success or failure of the creation process.
 */
public class CreateTaskPresenter implements CreateTaskOutputBoundary {
    private final CreateTaskViewModel createTaskViewModel;
    private ViewManagerModel viewManagerModel;

    private final MainPageViewModel mainPageViewModel;

    /**
     * Constructor method that creates CreateTaskPresenter class with appropriate view manager model and view model.
     * @param viewManagerModel ViewManagerModel responsible for managing which view to be active.
     * @param createTaskViewModel ViewModel specific for create task use case.
     */
    public CreateTaskPresenter(ViewManagerModel viewManagerModel, CreateTaskViewModel createTaskViewModel, MainPageViewModel mainPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createTaskViewModel = createTaskViewModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    /**
     * Prepares the view when the creation process failed.
     * @param error String that indicates error
     */
    @Override
    public void prepareFailView(String error) {
        CreateTaskState createTaskState = createTaskViewModel.getState();
        createTaskState.setCreateTaskError(error);
        createTaskViewModel.setState(createTaskState);
        createTaskViewModel.firePropertyChanged();

    }

    /**
     * Prepares the view when the creation process succeeded.
     * @param createTaskOutputData OutputData that will send to the user.
     */
    @Override
    public void prepareSuccessView(CreateTaskOutputData createTaskOutputData) {
            MainPageState mainPageState = mainPageViewModel.getState();
            ArrayList<String> taskList = mainPageState.getTaskList();
            taskList.add(createTaskOutputData.getTaskName());
            mainPageState.setTaskList(taskList);
            mainPageViewModel.setState(mainPageState);
            mainPageViewModel.firePropertyChanged();
            viewManagerModel.setActiveView("Main Page");
            viewManagerModel.firePropertyChanged();
        }

}