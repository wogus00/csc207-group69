package interface_adapter.remove_email;

import interface_adapter.remove_email.RemoveEmailState;
import interface_adapter.remove_email.RemoveEmailViewModel;
import use_case.remove_email.RemoveEmailOutputBoundary;

/**
 * The {@code RemoveEmailPresenter} class implements the {@code RemoveEmailOutputBoundary} interface.
 * It is responsible for handling the presentation logic for the remove email use case.
 * This presenter updates the view model based on the success or failure of removing an email.
 */
public class RemoveEmailPresenter implements RemoveEmailOutputBoundary {
    private RemoveEmailViewModel viewModel;

    /**
     * Constructs a {@code RemoveEmailPresenter} with the specified view model.
     *
     * @param viewModel The view model that this presenter will update based on the outcome
     *                  of the remove email use case
     */
    public RemoveEmailPresenter(RemoveEmailViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Updates the view model to reflect the successful removal of an email.
     * This method should be called when the remove email operation is successful,
     * triggering any observers that the view model state has changed.
     */
    @Override
    public void prepareSuccessView() {
        viewModel.firePropertyChanged();
    }

    /**
     * Updates the view model to reflect the failure to remove an email.
     * This method should be called when the remove email operation fails,
     * storing the error message in the view model and triggering any observers
     * that the view model state has changed.
     *
     * @param error The error message to be displayed in the view
     */
    @Override
    public void prepareFailView(String error) {
        RemoveEmailState removeProjectState = viewModel.getState();
        removeProjectState.setError(error);
        viewModel.firePropertyChanged();
    }
}