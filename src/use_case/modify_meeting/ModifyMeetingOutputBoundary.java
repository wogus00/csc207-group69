package use_case.modify_meeting;

public interface ModifyMeetingOutputBoundary {
    void prepareFailView(String error);

    void prepareSuccessView(ModifyMeetingOutputData modifyMeetingOutputData);
}
