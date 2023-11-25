package interface_adapter.create_task;

import interface_adapter.ViewManagerModel;
import use_case.create_task.CreateTaskOutputBoundary;
import use_case.create_task.CreateTaskOutputData;

/**
 * Presenter class for Create Task use case.
 * This class handles presentation logic adhering Clean Architecture by updating the view model appropriately based on success or failure of the creation process.
 */
public class CreateTaskPresenter implements CreateTaskOutputBoundary {
    private final CreateTaskViewModel createTaskViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor method that creates CreateTaskPresenter class with appropriate view manager model and view model.
     * @param viewManagerModel ViewManagerModel responsible for managing which view to be active.
     * @param createTaskViewModel ViewModel specific for create task use case.
     */
    public CreateTaskPresenter(ViewManagerModel viewManagerModel, CreateTaskViewModel createTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createTaskViewModel = createTaskViewModel;
    }

    /**
     * Prepares the view when the creation process failed.
     * @param error String that indicates error
     */
    @Override
    public void prepareFailView(String error) {
    }

    /**
     * Prepares the view when the creation process succeeded.
     * @param createTaskOutputData OutputData that will send to the user.
     */
    @Override
    public void prepareSuccessView(CreateTaskOutputData createTaskOutputData) {
    }
}