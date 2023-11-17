package interface_adapter.delete_announcement;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementState;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import use_case.delete_announcement.DeleteAnnouncementOutputBoundary;
import use_case.delete_announcement.DeleteAnnouncementOutputData;

public class DeleteAnnouncementPresenter implements DeleteAnnouncementOutputBoundary {
    private final DeleteAnnouncementViewModel deleteAnnouncementViewModel;

    private ViewManagerModel viewManagerModel;

    private final CreateAnnouncementViewModel createAnnouncementViewModel;

    public DeleteAnnouncementPresenter(DeleteAnnouncementViewModel deleteAnnouncementViewModel,
                                       CreateAnnouncementViewModel createAnnouncementViewModel,
                                       ViewManagerModel viewManagerModel){
        this.deleteAnnouncementViewModel = deleteAnnouncementViewModel;
        this.viewManagerModel = viewManagerModel;
        this.createAnnouncementViewModel = createAnnouncementViewModel;
    }

    @Override
    public void prepareNotFoundView(String announcementId) {
        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncementError("Announcement with ID " + announcementId + " not found.");
        deleteAnnouncementViewModel.firePropertyChanged();
    }

    // Implementation for when the user is unauthorized to delete the announcement
    @Override
    public void prepareUnauthorizedView(String announcementId) {
        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncementError("Unauthorized to delete announcement with ID " + announcementId + ".");
        deleteAnnouncementViewModel.firePropertyChanged();
    }

    // Implementation for handling generic errors
    @Override
    public void prepareErrorView(String announcementId, String errorMessage) {
        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncementError("Error deleting announcement with ID " + announcementId + ": " + errorMessage);
        deleteAnnouncementViewModel.firePropertyChanged();
    }

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

    @Override
    public void prepareSuccessView(DeleteAnnouncementOutputData deleteAnnouncementOutputData){
        CreateAnnouncementState createAnnouncementState = createAnnouncementViewModel.getState();
        this.createAnnouncementViewModel.setState(createAnnouncementState);
        createAnnouncementViewModel.firePropertyChanged();

        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncement(deleteAnnouncementOutputData.getAnnouncement());
        deleteAnnouncementViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(createAnnouncementViewModel.getViewName());
        deleteAnnouncementViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error){
        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncementError(error);
        deleteAnnouncementViewModel.firePropertyChanged();;
    }
}
