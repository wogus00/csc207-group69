package interface_adapter.create_meeting;

import use_case.create_meeting.CreateMeetingInputBoundary;
import use_case.create_meeting.CreateMeetingInputData;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Controller class is responsible for creating a meeting.
 * This class takes inputs from the user about the details of the meeting and
 * passes it to the Create Meeting use case interactor.
 *
 */

public class CreateMeetingController {

    final CreateMeetingInputBoundary userCreateMeetingUseCaseInteractor;
    /**
     * Constructs a CreateMeetingController with the Create Meeting use case interactor.
     *
     * @param userCreateMeetingUseCaseInteractor Interactor class that is responsible for
     *                                       the business logic of creating meeting.
     */
    public CreateMeetingController(CreateMeetingInputBoundary userCreateMeetingUseCaseInteractor) {
        this.userCreateMeetingUseCaseInteractor = userCreateMeetingUseCaseInteractor;
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
    public void execute(String meetingName, ArrayList<String> participantEmail, String meetingDate, String startTime, String endTime, String projectName) throws ExecutionException, InterruptedException {
        CreateMeetingInputData createMeetingInputData = new CreateMeetingInputData(
                meetingName, participantEmail, meetingDate, startTime, endTime, projectName);

        userCreateMeetingUseCaseInteractor.execute(createMeetingInputData);
    }
}
