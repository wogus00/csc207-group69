package interface_adapter.complete_task;

import interface_adapter.ViewManagerModel;
import use_case.complete_task.CompleteTaskOutputBoundary;
import use_case.complete_task.CompleteTaskOutputData;

/**
 * Presenter class for Complete Task use case.
 * This class handles presentation logic adhering Clean Architecture by updating the view model appropriately based on success or failure of the completion process.
 */
public class CompleteTaskPresenter implements CompleteTaskOutputBoundary {
    private final CompleteTaskViewModel completeTaskViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor method that creates CompleteTaskPresenter class with appropriate view manager model and view model.
     *
     * @param viewManagerModel ViewManagerModel responsible for managing which view to be active.
     * @param completeTaskViewModel ViewModel specific for complete task use case.
     */
    public CompleteTaskPresenter(ViewManagerModel viewManagerModel, CompleteTaskViewModel completeTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.completeTaskViewModel = completeTaskViewModel;
    }

    /**
     * Prepares the view when the completing process failed.
     *
     * @param errorMessage String of errorMessage
     */
    @Override
    public void prepareFailView(String errorMessage) {

    }

    /**
     * Prepares the view when the completing process succeeded.
     *
     * @param completeTaskOutputData OutputData that will send to the user.
     */
    @Override
    public void prepareSuccessView(CompleteTaskOutputData completeTaskOutputData) {

    }
}
