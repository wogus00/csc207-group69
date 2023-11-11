package use_case.create_meeting;

public interface CreateMeetingOutputBoundary {
    void prepareFailView(String error);

    void prepareSuccessView(CreateMeetingOutputData createMeetingOutputData);
}
