package interface_adapter.remove_email;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.remove_email.RemoveEmailState;
import interface_adapter.remove_email.RemoveEmailViewModel;
import use_case.remove_email.RemoveEmailOutputBoundary;

import javax.swing.text.View;
import java.util.ArrayList;

/**
 * The {@code RemoveEmailPresenter} class implements the {@code RemoveEmailOutputBoundary} interface.
 * It is responsible for handling the presentation logic for the remove email use case.
 * This presenter updates the view model based on the success or failure of removing an email.
 */
public class RemoveEmailPresenter implements RemoveEmailOutputBoundary {
    private RemoveEmailViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private MainPageViewModel mainPageViewModel;

    /**
     * Constructs a {@code RemoveEmailPresenter} with the specified view model.
     *
     * @param viewModel The view model that this presenter will update based on the outcome
     *                  of the remove email use case
     */
    public RemoveEmailPresenter(RemoveEmailViewModel viewModel, ViewManagerModel viewManagerModel, MainPageViewModel mainPageViewModel) {

        this.viewModel = viewModel;
        this.mainPageViewModel = mainPageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Updates the view model to reflect the successful removal of an email.
     * This method should be called when the remove email operation is successful,
     * triggering any observers that the view model state has changed.
     */
    @Override
    public void prepareSuccessView(String removedMember) {
        MainPageState mainPageState = mainPageViewModel.getState();
        ArrayList<String> memberList = mainPageState.getMemberEmail();
        memberList.remove(removedMember);
        mainPageState.setMemberEmail(memberList);
        mainPageViewModel.setState(mainPageState);
        mainPageViewModel.firePropertyChanged();
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