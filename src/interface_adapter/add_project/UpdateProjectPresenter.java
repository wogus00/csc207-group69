package interface_adapter.update_project;

import java.util.List;

public class UpdateProjectPresenter {
    private UpdateProjectViewModel viewModel;

    public UpdateProjectPresenter(UpdateProjectViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void presentUpdateResult(boolean isSuccess, List<String> errorMessages) {
        if (isSuccess) {
            viewModel.setProjectUpdateSuccess(true);
            viewModel.setErrorMessage("");
        } else {
            viewModel.setProjectUpdateSuccess(false);
            viewModel.setErrorMessage(String.join(", ", errorMessages));
        }
    }
}
