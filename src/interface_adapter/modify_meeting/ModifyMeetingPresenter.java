package interface_adapter.modify_meeting;

import interface_adapter.ViewManagerModel;
import use_case.create_meeting.CreateMeetingOutputBoundary;
import use_case.modify_meeting.ModifyMeetingOutputBoundary;
import use_case.modify_meeting.ModifyMeetingOutputData;

/**
 * Presenter class for Modify Meeting use case.
 * This class updates the view model and state based on whether the meeting was successfully modifyd or not.
 */
public class ModifyMeetingPresenter implements ModifyMeetingOutputBoundary {

    private final ModifyMeetingViewModel modifyMeetingViewModel;
    private ViewManagerModel viewManagerModel;
    /**
     * Constructor method that modifys ModifyMeetingPresenter class with the view manager model and view model.
     *
     * @param modifyMeetingViewModel View model for the Modify Meeting use case.
     * @param viewManagerModel View manager model that is responsible for managing the active view.
     */
    public ModifyMeetingPresenter(ViewManagerModel viewManagerModel,
                                  ModifyMeetingViewModel modifyMeetingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.modifyMeetingViewModel = modifyMeetingViewModel;
    }

    /**
     * Prepares a success view when a meeting has been modifyd successfully.
     *
     * @param response The output data from the Modify Meeting use case.
     */
    @Override
    public void prepareSuccessView(ModifyMeetingOutputData response) {
    }
    /**
     * Prepares a failure view when the creation of a meeting fails.
     *
     * @param error A string describing the error encountered during the creation of the meeting.
     */
    @Override
    public void prepareFailView(String error) {
    }
}
