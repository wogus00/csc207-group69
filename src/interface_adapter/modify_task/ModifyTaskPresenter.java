package interface_adapter.modify_task;

import interface_adapter.ViewManagerModel;
import use_case.modify_task.ModifyTaskOutputBoundary;
import use_case.modify_task.ModifyTaskOutputData;

/**
 * Presenter class for Modify Task use case.
 * This class handles presentation logic adhering Clean Architecture by updating the view model appropriately based on success or failure of the creation process.
 */
public class ModifyTaskPresenter implements ModifyTaskOutputBoundary {
    private final ModifyTaskViewModel modifyTaskViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor method that modifys ModifyTaskPresenter class with appropriate view manager model and view model.
     * @param viewManagerModel ViewManagerModel responsible for managing which view to be active.
     * @param modifyTaskViewModel ViewModel specific for modify task use case.
     */
    public ModifyTaskPresenter(ViewManagerModel viewManagerModel, ModifyTaskViewModel modifyTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.modifyTaskViewModel = modifyTaskViewModel;
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
     * @param modifyTaskOutputData OutputData that will send to the user.
     */
    @Override
    public void prepareSuccessView(ModifyTaskOutputData modifyTaskOutputData) {
    }
}