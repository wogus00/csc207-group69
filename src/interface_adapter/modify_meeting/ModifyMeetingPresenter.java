package interface_adapter.modify_meeting;

import interface_adapter.ViewManagerModel;
import interface_adapter.modify_meeting.ModifyMeetingState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.main_page.MainPageState;
import use_case.modify_meeting.ModifyMeetingOutputBoundary;
import use_case.modify_meeting.ModifyMeetingOutputBoundary;
import use_case.modify_meeting.ModifyMeetingOutputData;

/**
 * Presenter class for Modify Meeting use case.
 * This class updates the view model and state based on whether the meeting was successfully modifyd or not.
 */
public class ModifyMeetingPresenter implements ModifyMeetingOutputBoundary {

    private final ModifyMeetingViewModel modifyMeetingViewModel;
    private ViewManagerModel viewManagerModel;
    private final MainPageViewModel mainPageViewModel;
    /**
     * Constructor method that modifies ModifyMeetingPresenter class with the view manager model and view model.
     *
     * @param modifyMeetingViewModel View model for the Modify Meeting use case.
     * @param viewManagerModel View manager model that is responsible for managing the active view.
     * @param mainPageViewModel View model for the main page.
     */
    public ModifyMeetingPresenter(ViewManagerModel viewManagerModel,
                                  ModifyMeetingViewModel modifyMeetingViewModel,
                                  MainPageViewModel mainPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.modifyMeetingViewModel = modifyMeetingViewModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    /**
     * Prepares a success view when a meeting has been modified successfully.
     *
     * @param response The output data from the Modify Meeting use case.
     */
    @Override
    public void prepareSuccessView(ModifyMeetingOutputData response) {
        MainPageState mainPageState = new MainPageState();
        mainPageState.addAnnouncement(response.getMeetingName());
        mainPageViewModel.setState(mainPageState);
        viewManagerModel.setActiveView(modifyMeetingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    /**
     * Prepares a failure view when the creation of a meeting fails.
     *
     * @param error A string describing the error encountered during the creation of the meeting.
     */
    @Override
    public void prepareFailView(String error) {
        ModifyMeetingState modifyMeetingState = modifyMeetingViewModel.getState();
        modifyMeetingState.setMeetingNameError(error);
        modifyMeetingViewModel.firePropertyChanged();
    }
}
