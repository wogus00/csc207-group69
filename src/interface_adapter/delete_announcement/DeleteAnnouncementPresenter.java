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
    public void prepareSuccessView(DeleteAnnouncementOutputData deleteAnnouncementOutputData){
        CreateAnnouncementState createAnnouncementState = createAnnouncementViewModel.getState();
        this.createAnnouncementViewModel.setState(createAnnouncementState);
        createAnnouncementViewModel.firePropertyChanged();

        DeleteAnnouncementState deleteAnnouncementState = deleteAnnouncementViewModel.getState();
        deleteAnnouncementState.setAnnouncement(deleteAnnouncementOutputData.outputAnnouncement());
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
