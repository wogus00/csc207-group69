package interface_adapter.modify_meeting;

import interface_adapter.ViewManagerModel;
import interface_adapter.modify_meeting.ModifyMeetingState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.main_page.MainPageState;
import use_case.modify_meeting.ModifyMeetingOutputBoundary;
import use_case.modify_meeting.ModifyMeetingOutputBoundary;
import use_case.modify_meeting.ModifyMeetingOutputData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        MainPageState mainPageState = mainPageViewModel.getState();
        String dateStr = response.getMeetingDate();
        String timeStr = response.getEndTime();
        // Define the formatters for parsing
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Parse the date and time separately
        LocalDate date = LocalDate.parse(dateStr, dateFormatter);
        LocalTime time = LocalTime.parse(timeStr, timeFormatter);

        // Combine them into a LocalDateTime object
        LocalDateTime combinedDateTime = LocalDateTime.of(date, time);

        // Get the current LocalDateTime
        LocalDateTime currentDateTime = LocalDateTime.now();
        ArrayList<String> meetingList = mainPageState.getMeetingList();
        if (!combinedDateTime.isBefore(currentDateTime) && !meetingList.contains(response.getMeetingName())) {
            meetingList.add(response.getMeetingName());
            mainPageState.setMeetingList(meetingList);
        }
        mainPageViewModel.setState(mainPageState);
        mainPageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("Main Page");
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
        modifyMeetingViewModel.setState(modifyMeetingState);
        modifyMeetingViewModel.firePropertyChanged();
    }
}
