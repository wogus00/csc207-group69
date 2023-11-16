package interface_adapter.create_meeting;

import interface_adapter.ViewManagerModel;
import use_case.create_meeting.CreateMeetingOutputBoundary;
import use_case.create_meeting.CreateMeetingOutputData;

public class CreateMeetingPresenter extends CreateMeetingOutputBoundary {

    private final CreateMeetingViewModel createMeetingViewModel;

    public CreateMeetingPresenter(ViewManagerModel viewManagerModel,
                                  CreateMeetingViewModel createMeetingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createMeetingViewModel = createMeetingViewModel;
    }

    @Override
    public void prepareSuccessView(CreateProjectOutputData response) {
        // On success, switch to the project dashboard view.
    }

    @Override
    public void prepareFailView(String error) {
        createMeetingState createMeetingState = CreateMeetingViewModel.getState();
        createMeetingState.setMeetingNameError(error);
        createMeetingViewModel.firePropertyChanged();
    }
}
