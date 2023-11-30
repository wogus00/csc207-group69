package interface_adapter.complete_task;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.complete_task.CompleteTaskOutputBoundary;
import use_case.complete_task.CompleteTaskOutputData;

import java.util.ArrayList;

/**
 * Presenter class for Complete Task use case.
 * This class handles presentation logic adhering Clean Architecture by updating the view model appropriately based on success or failure of the completion process.
 */
public class CompleteTaskPresenter implements CompleteTaskOutputBoundary {
    private final CompleteTaskViewModel completeTaskViewModel;
    private ViewManagerModel viewManagerModel;

    private MainPageViewModel mainPageViewModel;

    /**
     * Constructor method that creates CompleteTaskPresenter class with appropriate view manager model and view model.
     *
     * @param viewManagerModel ViewManagerModel responsible for managing which view to be active.
     * @param completeTaskViewModel ViewModel specific for complete task use case.
     */
    public CompleteTaskPresenter(ViewManagerModel viewManagerModel, CompleteTaskViewModel completeTaskViewModel, MainPageViewModel mainPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.completeTaskViewModel = completeTaskViewModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    /**
     * Prepares the view when the completing process failed.
     *
     * @param errorMessage String of errorMessage
     */
    @Override
    public void prepareFailView(String errorMessage) {
        CompleteTaskState completeTaskState = completeTaskViewModel.getState();
        completeTaskState.setTaskNameError(errorMessage);
        completeTaskViewModel.setState(completeTaskState);
        completeTaskViewModel.firePropertyChanged();

    }

    /**
     * Prepares the view when the completing process succeeded.
     *
     * @param completeTaskOutputData OutputData that will send to the user.
     */
    @Override
    public void prepareSuccessView(CompleteTaskOutputData completeTaskOutputData) {
        // Logs the leader in
        MainPageState mainPageState = mainPageViewModel.getState();
        ArrayList<String> taskList = mainPageState.getTaskList();
        taskList.remove(completeTaskOutputData.getTaskName());
        mainPageState.setTaskList(taskList);
        this.mainPageViewModel.setState(mainPageState);
        this.mainPageViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(this.mainPageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
