package interface_adapter.create_meeting;

import use_case.create_meeting.CreateMeetingInputBoundary;
import use_case.create_meeting.CreateMeetingInputData;
import java.util.*;
import java.sql.Time;

public class CreateMeetingController {

    final CreateMeetingInputBoundary userCreateMeetingUseCaseInteractor;
    public CreateMeetingController(CreateMeetingInputBoundary userCreateMeetingUseCaseInteractor) {
        this.userCreateMeetingseCaseInteractor = userCreateMeetingUseCaseInteractor;
    }

    public void execute(String meetingName, String participantEmail, Date meetingDate, Time startTime, Time endTime) {
        CreateMeetingInputData createMeetingInputData = new CreateMeetingInputData(
                meetingName, participantEmail, meetingDate, startTime, endTime, projectName);

        userCreateMeetingUseCaseInteractor.execute(createMeetingInputData);
    }
}
