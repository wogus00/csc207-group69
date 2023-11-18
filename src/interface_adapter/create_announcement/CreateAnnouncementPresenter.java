package interface_adapter.create_announcement;


import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_announcement.CreateAnnouncementOutputBoundary;
import use_case.create_announcement.CreateAnnouncementOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateAnnouncementPresenter implements CreateAnnouncementOutputBoundary  {

    private final CreateAnnouncementViewModel createAnnouncementViewModel;

    private ViewManagerModel viewManagerModel;

    private MainPageViewModel mainPageViewModel;

    public CreateAnnouncementPresenter(ViewManagerModel viewManagerModel,
                                       CreateAnnouncementViewModel createAnnouncementViewModel,
                                       MainPageViewModel mainPageViewModel) {
        this.createAnnouncementViewModel = createAnnouncementViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    @Override
    public void prepareSuccessView(CreateAnnouncementOutputData response) {
        // on Success, switch to the dashboard
        MainPageState mainPageState = new MainPageState();
        viewManagerModel.setActiveView(createAnnouncementViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(CreateAnnouncementOutputData createAnnouncementOutputData, String error) {

    }

    @Override
    public void prepareFailView(String error) {
        CreateAnnouncementState createAnnouncementState = createAnnouncementViewModel.getState();
        createAnnouncementState.setAnnouncementTitleError(error);
        createAnnouncementViewModel.firePropertyChanged();
    }
}
