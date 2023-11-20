package interface_adapter.create_meeting;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_meeting.CreateMeetingOutputBoundary;
import interface_adapter.create_meeting.CreateMeetingState;
import use_case.create_meeting.CreateMeetingOutputData;

public class CreateMeetingPresenter implements CreateMeetingOutputBoundary {

    private final CreateMeetingViewModel createMeetingViewModel;
    private final MainPageViewModel mainPageViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateMeetingPresenter(ViewManagerModel viewManagerModel,
                                  CreateMeetingViewModel createMeetingViewModel,
                                  MainPageViewModel mainPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createMeetingViewModel = createMeetingViewModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    @Override
    public void prepareSuccessView(CreateMeetingOutputData response) {
        MainPageState mainPageState = new MainPageState();
        mainPageState.setMeetingName(response.getMeetingName());
        mainPageState.setParticipantEmail(response.getParticipantEmail());
        mainPageState.setMeetingDate(response.getMeetingDate());
        mainPageState.setStartTime(response.getStartTime());
        mainPageState.setEndTime(response.getEndtime());
        mainPageState.setProjectName(response.getProjectName());
        mainPageViewModel.setState(mainPageState);
        viewManagerModel.setActiveView(mainPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        createMeetingState createMeetingState = CreateMeetingViewModel.getState();
        createMeetingState.setMeetingNameError(error);
        createMeetingViewModel.firePropertyChanged();
    }
}
