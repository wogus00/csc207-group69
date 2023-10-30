package use_case.create_meeting;

import entity.MeetingName;

public class CreateMeetingInteractor implements CreateMeetingBoundary {
    final CreateMeetingDataAccessInterface meetingDataAccessObject;
    final CreateMeetingOutputBoundary create_meetingPresenter;

    public CreateMeetingInteractor(CreateMeetingDataAccessInterface meetingDataAccessInterface,
                                   CreatePMeetingOutputBoundary create_meetingOutputBoundary) {
        this.meetingDataAccessObject = meetingDataAccessInterface;
        this.meetingPresenter = create_meetingOutputBoundary;
    }

    @Override
    public void execute(CreateMeetingInputData CreateMeetingInputData) {
        String project = create_meetingInputData.getMeeting_name();
        if (!meetingDataAccessObject.existsByName(meeting)) {
            create_meetingPresenter.prepareFailView(project + ": this meeting does not exist.");
        }
    }
}