package interface_adapter.add_email;

import app.Main;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.add_email.AddEmailOutputBoundary;

import java.util.ArrayList;

/**
 * The {@code AddEmailPresenter} class implements the {@code AddEmailOutputBoundary} interface.
 * It is responsible for handling the presentation logic for the add email use case. This presenter
 * updates the view model based on the success or failure of adding an email.
 */
public class AddEmailPresenter implements AddEmailOutputBoundary {
    private AddEmailViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private MainPageViewModel mainPageViewModel;

    /**
     * Constructs an {@code AddEmailPresenter} with the specified view model.
     *
     * @param viewModel The view model that this presenter will update based on the outcome
     *                  of the add email use case
     */
    public AddEmailPresenter(AddEmailViewModel viewModel, ViewManagerModel viewManagerModel, MainPageViewModel mainPageViewModel) {
        this.mainPageViewModel = mainPageViewModel;
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
    }

    /**
     * Updates the view model to reflect the successful addition of an email.
     * This method should be called when the add email operation is successful,
     * triggering any observers that the view model state has changed.
     */
    @Override
    public void prepareSuccessView(String newMember) {
        MainPageState mainPageState = mainPageViewModel.getState();
        ArrayList<String> memberList = mainPageState.getMemberEmail();
        memberList.add(newMember);
        mainPageState.setMemberEmail(memberList);
        mainPageViewModel.setState(mainPageState);
        mainPageViewModel.firePropertyChanged();
        viewModel.firePropertyChanged();
        viewModel.firePropertyChanged();
    }

    /**
     * Updates the view model to reflect the failure to add an email.
     * This method should be called when the add email operation fails,
     * storing the error message in the view model and triggering any observers
     * that the view model state has changed.
     *
     * @param error The error message to be displayed in the view
     */
    @Override
    public void prepareFailView(String error) {
        AddEmailState addProjectState = viewModel.getState();
        addProjectState.setError(error);
        viewModel.firePropertyChanged();
    }
}
