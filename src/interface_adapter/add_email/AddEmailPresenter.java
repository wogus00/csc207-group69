package interface_adapter.add_email;

import use_case.add_email.AddEmailOutputBoundary;

public class AddEmailPresenter implements AddEmailOutputBoundary {
    private AddEmailViewModel viewModel;

    public AddEmailPresenter(AddEmailViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddEmailState addProjectState = viewModel.getState();
        addProjectState.setError(error);
        viewModel.firePropertyChanged();
    }
}
