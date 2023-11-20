package use_case.create_meeting;

import entity.MeetingFactory;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateMeetingInteractor implements CreateMeetingInputBoundary {
    final CreateMeetingDataAccessInterface createMeetingDataAccessObject;
    final CreateMeetingGmailDataAccessInterface createMeetingGmailAccessObject;
    final CreateMeetingOutputBoundary createMeetingPresenter;
    final MeetingFactory meetingFactory;


    public CreateMeetingInteractor(CreateMeetingDataAccessInterface createMeetingDataAccessObject,
                                   CreateMeetingGmailDataAccessInterface createMeetingGmailAccessObject,
                                   CreateMeetingOutputBoundary createMeetingPresenter,
                                   MeetingFactory meetingFactory) {
        this.createMeetingPresenter = createMeetingPresenter1;
        this.createMeetingDataAccessObject = meetingDataAccessInterface;
        this.createMeetingGmailAccessObject = createMeetingGmailAccessObject;
        this.createMeetingPresenter = createMeetingOutputBoundary;
        this.meetingFactory = meetingFactory;
    }

    @Override
    public void execute(CreateMeetingInputData createMeetingInputData) {
        String meetingName = CreateMeetingInputData.getMeetingName();
        ArrayList<String> participantEmail = CreateMeetingInputData.getParticipantEmail();
        String fromEmail = createMeetingInputData.getParticipantEmail().get(0);
        String meetingDate = CreateMeetingInputData.getMeetingDate();
        String startTime = CreateMeetingInputData.getStartTime();
        String endTime = CreateMeetingInputData.getEndTime();
        String projectName = CreateMeetingInputData.getProjectName();

        if (!createMeetingDataAccessObject.meetingNameTaken(projectName, taskName)) {
            createMeetingPresenter.prepareFailView("Meeting name is already taken");
        } else if (!createMeetingDataAccessObject.participantsExist(projectName, participantEmail)) {
            createMeetingPresenter.prepareFailView("Member does not exist");
        } else {
            Meeting newMeeting = MeetingFactory.create(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);
            createMeetingDataAccessObject.saveMeeting(projectName, newMeeting);

            for (String toEmail: participantEmail) {
                try {
                    createMeetingGmailAccessObject.sendMeetingCreationEmail(toEmail, fromEmail, meetingName);
                } catch (MessagingException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            CreateMeetingOutputData createMeetingOutputData = new CreateMeetingOutputData();
            createMeetingPresenter.prepareSuccessView(createMeetingOutputData);
        }

    }
}