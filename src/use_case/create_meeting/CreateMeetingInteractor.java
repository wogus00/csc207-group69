package use_case.create_meeting;

import entity.MeetingFactory;
import entity.MeetingName;

import java.util.ArrayList;

public class CreateMeetingInteractor implements CreateMeetingInputBoundary {
    final CreateMeetingDataAccessInterface createMeetingDataAccessObject;
    final CreateMeetingGmailDataAccessInterface createMeetingGmailAccessObject;
    final CreateMeetingOutputBoundary createMeetingPresenter;
    final MeetingFactory meetingFactory;


    public CreateMeetingInteractor(CreateMeetingDataAccessInterface createMeetingDataAccessObject,
                                   CreateMeetingGmailDataAccessInterface createMeetingGmailAccessObject,
                                   CreateMeetingOutputBoundary createMeetingPresenter,
                                   CreateMeetingOutputBoundary createMeetingPresenter1, MeetingFactory meetingFactory) {
        this.createMeetingPresenter = createMeetingPresenter1;
        this.meetingDataAccessObject = meetingDataAccessInterface;
        this.createMeetingGmailAccessObject = createMeetingGmailAccessObject;
        this.meetingPresenter = createMeetingOutputBoundary;
        this.meetingFactory = meetingFactory;
    }

    public CreateMeetingInteractor(CreateMeetingDataAccessInterface createMeetingDataAccessObject, CreateMeetingGmailDataAccessInterface gmailDataAccessObject, CreateMeetingOutputBoundary createMeetingOutputBoundary, MeetingFactory meetingFactory) {
    }

    @Override
    public void execute(CreateMeetingInputData CreateMeetingInputData) {
        String meetingName = CreateMeetingInputData.getMeetingName();
        ArrayList<String> participantEmail = CreateMeetingInputData.getParticipantEmail();
        LocalDate meetingDate = CreateMeetingInputData.getMeetingDate();
        Time startTime = CreateMeetingInputData.getStartTime();
        Time endTime = CreateMeetingInputData.getEndTime();
        String projectName = CreateMeetingInputData.getProjectName();

        if (!createMeetingDataAccessObject.meetingNameTaken(projectName, taskName)) {
            createMeetingPresenter.prepareFailView("Meeting name is already taken");
        } else if (!createMeetingDataAccessObject.participantsExist(projectName, participantEmail)) {
            createMeetingPresenter.prepareFailView("Member does not exist");
        } else {
            Meeting newMeeting = MeetingFactory.create(projectName, meetingName, meetingDate, startTime, endTime);
            createMeetingDataAccessObject.saveMeeting(projectName, newMeeting);

            for (String email: participantEmail) {
                try {
                    createMeetingGmailAccessObject.sendMeetingCreationEmail(participantEmail);
                } catch (MessagingException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            CreateMeetingOutputData createMeetingOutputData = new CreateMeetingOutputData();
            createMeetingPresenter.prepareSuccessView(createMeetingOutputData);
        }

    }
}