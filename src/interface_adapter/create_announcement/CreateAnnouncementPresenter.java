package interface_adapter.create_announcement;


import interface_adapter.ViewManagerModel;
import use_case.create_announcement.CreateAnnouncementOutputBoundary;
import use_case.create_announcement.CreateAnnouncementOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateAnnouncementPresenter implements CreateAnnouncementOutputBoundary  {

    private final CreateAnnouncementViewModel createAnnouncementViewModel;

    private ViewManagerModel viewManagerModel;

    public CreateAnnouncementPresenter(ViewManagerModel viewManagerModel,
                                       CreateAnnouncementViewModel createAnnouncementViewModel) {
        this.createAnnouncementViewModel = createAnnouncementViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateAnnouncementOutputData response) {
        // on Success, switch to the dashboard
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        CreateAnnouncementState createAnnouncementState = createAnnouncementViewModel.getState();
        viewManagerModel.setActiveView(createAnnouncementViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CreateAnnouncementState createAnnouncementState = createAnnouncementViewModel.getState();
        createAnnouncementState.setAnnouncementTitleError(error);
        createAnnouncementViewModel.firePropertyChanged();
    }
}
