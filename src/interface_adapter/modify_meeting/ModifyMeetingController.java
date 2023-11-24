package interface_adapter.modify_meeting;

import use_case.modify_meeting.ModifyMeetingInputBoundary;
import use_case.modify_meeting.ModifyMeetingInputData;
import java.util.*;

/**
 * Controller class is responsible for creating a meeting.
 * This class takes inputs from the user about the details of the meeting and
 * passes it to the Modify Meeting use case interactor.
 *
 */

public class ModifyMeetingController {

    final ModifyMeetingInputBoundary userModifyMeetingUseCaseInteractor;
    /**
     * Constructs a ModifyMeetingController with the Modify Meeting use case interactor.
     *
     * @param userModifyMeetingUseCaseInteractor Interactor class that is responsible for
     *                                       the business logic of creating meeting.
     */
    public ModifyMeetingController(ModifyMeetingInputBoundary userModifyMeetingUseCaseInteractor) {
        this.userModifyMeetingUseCaseInteractor = userModifyMeetingUseCaseInteractor;
    }
    /**
     * Executes the process of creating a new meeting.
     *
     * @param meetingName                The title of the meeting.
     * @param participantEmail           The email addresses of the participants.
     * @param meetingDate                The date of the meeting.
     * @param startTime                  The starting time of the meeting.
     * @param endTime                    The ending time of the meeting.
     * @param projectName                The name of the project that this meeting is about.
     */
    public void execute(String meetingName, ArrayList<String> participantEmail, String meetingDate, String startTime, String endTime, String projectName) {
        ModifyMeetingInputData modifyMeetingInputData = new ModifyMeetingInputData(
                meetingName, participantEmail, meetingDate, startTime, endTime, projectName);

        userModifyMeetingUseCaseInteractor.execute(modifyMeetingInputData);
    }
}
