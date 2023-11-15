package use_case.create_meeting;

public class CreateMeetingOutputBoundary {
    void prepareFailView(String error);

    void prepareSuccessView(CreateMeetingOutputData createMeetingOutputData);
}
