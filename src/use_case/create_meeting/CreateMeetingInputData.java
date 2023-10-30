package use_case.create_meeting;

public class CreateMeetingInputData {

    final private String meeting;

    public CreateMeetingInputData(String meeting) {
        this.meeting = meeting;
    }

    String getMeeting() {
        return meeting;
    }
}
