package interface_adapter.delete_announcement;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementState;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.delete_announcement.DeleteAnnouncementOutputBoundary;
import use_case.delete_announcement.DeleteAnnouncementOutputData;

import java.util.ArrayList;

/**
 * Presenter for the Delete Announcement feature.
 * This class handles the presentation logic for the response from the delete announcement use case,
 * updating the view model based on the success or failure of the deletion process.
 */
public class DeleteAnnouncementPresenter implements DeleteAnnouncementOutputBoundary {
    private final DeleteAnnouncementViewModel deleteAnnouncementViewModel;

    private ViewManagerModel viewManagerModel;
    private MainPageViewModel mainPageViewModel;

    private final CreateAnnouncementViewModel createAnnouncementViewModel;

    /**
     * Constructs a DeleteAnnouncementPresenter with the necessary view models and manager.
     *
     * @param mainPageViewModel               The view model for main page.
     * @param deleteAnnouncementViewModel     The view model for deleting announcements.
     * @param createAnnouncementViewModel     The view model for creating announcements.
     * @param viewManagerModel                The model managing the views.
     */
    public DeleteAnnouncementPresenter(DeleteAnnouncementViewModel deleteAnnouncementViewModel,
                                       CreateAnnouncementViewModel createAnnouncementViewModel,
                                       ViewManagerModel viewManagerModel,
                                       MainPageViewModel mainPageViewModel){
        this.deleteAnnouncementViewModel = deleteAnnouncementViewModel;
        this.viewManagerModel = viewManagerModel;
        this.createAnnouncementViewModel = createAnnouncementViewModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    /**
     * Prepares the view in case the announcement to be deleted is not found.
     *
     * @param announcementId The ID of the announcement that was not found.
     */
    @Override
    public void prepareNotFoundView(String announcementId) {
        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncementError("Announcement with ID " + announcementId + " not found.");
        deleteAnnouncementViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view in case deleting announcement is not authorized.
     *
     * @param announcementId The ID of the announcement that is not authorized.
     */
    // Implementation for when the user is unauthorized to delete the announcement
    @Override
    public void prepareUnauthorizedView(String announcementId) {
        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncementError("Unauthorized to delete announcement with ID " + announcementId + ".");
        deleteAnnouncementViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view in case that we get errors during delete announcement.
     *
     * @param announcementId The ID of the announcement that is not deleted due to some error.
     */
    // Implementation for handling generic errors
    @Override
    public void prepareErrorView(String announcementId, String errorMessage) {
        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncementError("Error deleting announcement with ID " + announcementId + ": " + errorMessage);
        deleteAnnouncementViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view in case deleting announcement fails.
     *
     * @param deleteAnnouncementOutputData The ID of the announcement with which delete fails.
     */
    // Modified implementation for handling failures
    @Override
    public void prepareFailureView(DeleteAnnouncementOutputData deleteAnnouncementOutputData) {
        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        if (deleteAnnouncementOutputData.getIsDeleted()) {
            deleteAnnouncementState.setAnnouncementError("Failed to delete announcement: Unknown error.");
        } else {
            deleteAnnouncementState.setAnnouncementError("Failed to delete announcement with ID " + deleteAnnouncementOutputData.getAnnouncement().getId() + ".");
        }
        deleteAnnouncementViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view in case the announcement to be deleted successfully.
     *
     * @param deleteAnnouncementOutputData The ID of the announcement that was successfully deleted
     */
    @Override
    public void prepareSuccessView(DeleteAnnouncementOutputData deleteAnnouncementOutputData){
        CreateAnnouncementState createAnnouncementState = createAnnouncementViewModel.getState();
        this.createAnnouncementViewModel.setState(createAnnouncementState);
        createAnnouncementViewModel.firePropertyChanged();

        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncement(deleteAnnouncementOutputData.getAnnouncement());
        deleteAnnouncementViewModel.firePropertyChanged();

        MainPageState mainPageState = mainPageViewModel.getState();
        ArrayList<String> messages = mainPageState.getAnnouncements();
        if (messages.contains(deleteAnnouncementOutputData.getAnnouncement().getMessage())) {
            messages.remove(deleteAnnouncementOutputData.getAnnouncement().getMessage());
        }
        mainPageState.setAnnouncements(messages);
        mainPageViewModel.setState(mainPageState);
        mainPageViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView("Main Page");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view in case the delete announcement fails in any other factor.
     *
     * @param error The ID of the announcement that was failed in any other factor.
     */
    @Override
    public void prepareFailView(String error){
        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncementError(error);
        deleteAnnouncementViewModel.setState(deleteAnnouncementState);
        deleteAnnouncementViewModel.firePropertyChanged();;
    }
}
