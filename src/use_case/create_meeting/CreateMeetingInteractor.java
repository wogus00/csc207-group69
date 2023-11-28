package use_case.create_meeting;

import entity.Meeting;
import entity.MeetingFactory;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Public Interactor class for the Create Meeting use case.
 * It is responsible for executing the  business logic associated with creating a meeting.
 * Interacts with FirebaseDataAccessObject to create the task and save it to the database, GmailDataAccessObject to
 * send emails, and communicates with the presenter to update the view based on completion of the use case.
 */
public class CreateMeetingInteractor implements CreateMeetingInputBoundary {
    final CreateMeetingDataAccessInterface createMeetingDataAccessObject;
    final CreateMeetingGmailDataAccessInterface createMeetingGmailAccessObject;
    final CreateMeetingOutputBoundary createMeetingPresenter;
    final MeetingFactory meetingFactory;

    /**
     * Constructs new CreateMeetingInteractor class with specified data access objects and output boundary.
     *
     * @param createMeetingDataAccessObject Interface implemented by FirebaseDataAccessObject responsible for communicating with Google Firebase.
     * @param createMeetingGmailAccessObject Interface implemented by GmailDataAccessObject responsible for communicating with Gmail API.
     * @param createMeetingPresenter Output Boundary to present the result of the creating meeting.
     * @param meetingFactory TaskFactory entity responsible for creating the meeting.
     */
    public CreateMeetingInteractor(CreateMeetingDataAccessInterface createMeetingDataAccessObject,
                                   CreateMeetingGmailDataAccessInterface createMeetingGmailAccessObject,
                                   CreateMeetingOutputBoundary createMeetingPresenter,
                                   MeetingFactory meetingFactory) {
        this.createMeetingDataAccessObject = createMeetingDataAccessObject;
        this.createMeetingGmailAccessObject = createMeetingGmailAccessObject;
        this.createMeetingPresenter = createMeetingPresenter;
        this.meetingFactory = meetingFactory;
    }

    /**
     * Execution method that creates the meeting.
     * Determines if the meeting can be created by checking if the same meetingName already exists and whether
     * all invited participants are part of the project.
     * If all requirements are met, it creates the new meeting, saves it to the firebase, and send a notification
     * email to the participants.
     *
     * @param createMeetingInputData required information from CreateMeetingInputData class that will be used to
     *                               determine if the meeting can be created  by the
     *                               CreateMeetingInteractor class
     */
    @Override
    public void execute(CreateMeetingInputData createMeetingInputData){
        String meetingName = createMeetingInputData.getMeetingName();
        ArrayList<String> participantEmail = createMeetingInputData.getParticipantEmail();
        String fromEmail = createMeetingInputData.getParticipantEmail().get(0);
        String meetingDate = createMeetingInputData.getMeetingDate();
        String startTime = createMeetingInputData.getStartTime();
        String endTime = createMeetingInputData.getEndTime();
        String projectName = createMeetingInputData.getProjectName();

        if (!createMeetingDataAccessObject.meetingNameExists(projectName, meetingName)) {
            createMeetingPresenter.prepareFailView("Meeting name is already taken");
        } else if (!createMeetingDataAccessObject.memberExists(projectName, participantEmail)) {
            createMeetingPresenter.prepareFailView("Member does not exist");
        } else {
            Meeting newMeeting = meetingFactory.create(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);
            createMeetingDataAccessObject.saveMeeting(newMeeting);

            for (String toEmail: participantEmail) {
                try {
                    createMeetingGmailAccessObject.sendMeetingCreationEmail(toEmail, fromEmail, meetingName);
                } catch (IOException | MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
            CreateMeetingOutputData createMeetingOutputData = new CreateMeetingOutputData(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);
            createMeetingPresenter.prepareSuccessView(createMeetingOutputData);
        }
    }
}