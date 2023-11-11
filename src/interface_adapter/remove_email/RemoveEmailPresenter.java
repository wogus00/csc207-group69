package interface_adapter.remove_email;

import interface_adapter.remove_email.RemoveEmailState;
import interface_adapter.remove_email.RemoveEmailViewModel;
import use_case.remove_email.RemoveEmailOutputBoundary;

public class RemoveEmailPresenter implements RemoveEmailOutputBoundary {
    private RemoveEmailViewModel viewModel;

    public RemoveEmailPresenter(RemoveEmailViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        RemoveEmailState removeProjectState = viewModel.getState();
        removeProjectState.setError(error);
        viewModel.firePropertyChanged();
    }
}