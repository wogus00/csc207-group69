package interface_adapter.create_meeting;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.create_meeting.CreateMeetingState;
import use_case.create_meeting.CreateMeetingOutputBoundary;
import use_case.create_meeting.CreateMeetingOutputData;

/**
 * Presenter class for Create Meeting use case.
 * This class updates the view model and state based on whether the meeting was successfully created or not.
 */
public class CreateMeetingPresenter implements CreateMeetingOutputBoundary {

    private final CreateMeetingViewModel createMeetingViewModel;
    private final MainPageViewModel mainPageViewModel;
    private ViewManagerModel viewManagerModel;
    /**
     * Constructor method that creates CreateMeetingPresenter class with the view manager model and view model.
     *
     * @param createMeetingViewModel View model for the Create Meeting use case.
     * @param mainPageViewModel View model for the main page.
     * @param viewManagerModel View manager model that is responsible for managing the active view.
     */
    public CreateMeetingPresenter(ViewManagerModel viewManagerModel,
                                  CreateMeetingViewModel createMeetingViewModel,
                                  MainPageViewModel mainPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createMeetingViewModel = createMeetingViewModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    /**
     * Prepares a success view when a meeting has been created successfully.
     *
     * @param response The output data from the Create Meeting use case.
     */
    @Override
    public void prepareSuccessView(CreateMeetingOutputData response) {
        MainPageState mainPageState = new MainPageState();
        mainPageState.setMeetingName(response.getMeetingName());
        mainPageState.setParticipantEmail(response.getParticipantEmail());
        mainPageState.setMeetingDate(response.getMeetingDate());
        mainPageState.setStartTime(response.getStartTime());
        mainPageState.setEndTime(response.getEndTime());
        mainPageState.setMeetingProjectName(response.getProjectName());
        mainPageViewModel.setState(mainPageState);
        viewManagerModel.setActiveView(mainPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    /**
     * Prepares a failure view when the creation of a meeting fails.
     *
     * @param error A string describing the error encountered during the creation of the meeting.
     */
    @Override
    public void prepareFailView(String error) {
        CreateMeetingState createMeetingState = CreateMeetingViewModel.getState();
        createMeetingState.setMeetingNameError(error);
        createMeetingViewModel.firePropertyChanged();
    }
}
