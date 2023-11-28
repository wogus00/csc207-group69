package interface_adapter.set_leader;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.set_leader.SetLeaderOutputBoundary;
import interface_adapter.set_leader.SetLeaderState;
import interface_adapter.set_leader.SetLeaderViewModel;

/**
 * The {@code SetLeaderPresenter} class implements the {@code SetLeaderOutputBoundary} interface.
 * It is responsible for handling the presentation logic for the set leader use case.
 * This presenter updates the view model based on the success or failure of setting a new leader.
 */
public class SetLeaderPresenter implements SetLeaderOutputBoundary {
    private SetLeaderViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private MainPageViewModel mainPageViewModel;

    /**
     * Constructs a {@code SetLeaderPresenter} with the specified view model.
     *
     * @param viewModel The view model that this presenter will update based on the outcome
     *                  of the set leader use case
     */
    public SetLeaderPresenter(SetLeaderViewModel viewModel, ViewManagerModel viewManagerModel, MainPageViewModel mainPageViewModel) {

        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainPageViewModel = mainPageViewModel;

    }

    /**
     * Updates the view model to reflect the successful setting of a new project leader.
     * This method should be called when the set leader operation is successful,
     * triggering any observers that the view model state has changed.
     */
    @Override
    public void prepareSuccessView() {
        
        viewModel.firePropertyChanged();
    }

    /**
     * Updates the view model to reflect the failure to set a new project leader.
     * This method should be called when the set leader operation fails,
     * storing the error message in the view model and triggering any observers
     * that the view model state has changed.
     *
     * @param error The error message to be displayed in the view
     */
    @Override
    public void prepareFailView(String error) {
        SetLeaderState removeProjectState = viewModel.getState();
        removeProjectState.setError(error);
        viewModel.firePropertyChanged();
    }
}
