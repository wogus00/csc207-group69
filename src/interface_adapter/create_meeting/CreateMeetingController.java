package interface_adapter.create_meeting;

import use_case.create_meeting.CreateMeetingInputBoundary;
import use_case.create_meeting.CreateMeetingInputData;
import java.util.*;
import java.sql.Time;

public class CreateMeetingController {

    final CreateMeetingInputBoundary userCreateMeetingUseCaseInteractor;
    public CreateMeetingController(CreateMeetingInputBoundary userCreateMeetingUseCaseInteractor) {
        this.userCreateMeetingUseCaseInteractor = userCreateMeetingUseCaseInteractor;
    }

    public void execute(String meetingName, ArrayList<String> participantEmail, String meetingDate, String startTime, String endTime, String projectName) {
        CreateMeetingInputData createMeetingInputData = new CreateMeetingInputData(
                meetingName, participantEmail, meetingDate, startTime, endTime, projectName);

        userCreateMeetingUseCaseInteractor.execute(createMeetingInputData);
    }
}
