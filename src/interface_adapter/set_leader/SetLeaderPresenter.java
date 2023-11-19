package interface_adapter.set_leader;

import use_case.set_leader.SetLeaderOutputBoundary;
import interface_adapter.set_leader.SetLeaderState;
import interface_adapter.set_leader.SetLeaderViewModel;

public class SetLeaderPresenter implements SetLeaderOutputBoundary {
    private SetLeaderViewModel viewModel;

    public SetLeaderPresenter(SetLeaderViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SetLeaderState removeProjectState = viewModel.getState();
        removeProjectState.setError(error);
        viewModel.firePropertyChanged();
    }
}
