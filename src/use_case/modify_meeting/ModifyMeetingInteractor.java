package use_case.modify_meeting;

import entity.Meeting;
import entity.MeetingFactory;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Public Interactor class for the Modify Meeting use case.
 * It is responsible for executing the  business logic associated with creating a meeting.
 * Interacts with FirebaseDataAccessObject to modify the task and save it to the database, GmailDataAccessObject to
 * send emails, and communicates with the presenter to update the view based on completion of the use case.
 */
public class ModifyMeetingInteractor implements ModifyMeetingInputBoundary {
    final ModifyMeetingDataAccessInterface modifyMeetingDataAccessObject;
    final ModifyMeetingGmailDataAccessInterface modifyMeetingGmailAccessObject;
    final ModifyMeetingOutputBoundary modifyMeetingPresenter;
    final MeetingFactory meetingFactory;

    /**
     * Constructs new ModifyMeetingInteractor class with specified data access objects and output boundary.
     *
     * @param modifyMeetingDataAccessObject Interface implemented by FirebaseDataAccessObject responsible for communicating with Google Firebase.
     * @param modifyMeetingGmailAccessObject Interface implemented by GmailDataAccessObject responsible for communicating with Gmail API.
     * @param modifyMeetingPresenter Output Boundary to present the result of the creating meeting.
     * @param meetingFactory TaskFactory entity responsible for creating the meeting.
     */
    public ModifyMeetingInteractor(ModifyMeetingDataAccessInterface modifyMeetingDataAccessObject,
                                   ModifyMeetingGmailDataAccessInterface modifyMeetingGmailAccessObject,
                                   ModifyMeetingOutputBoundary modifyMeetingPresenter,
                                   MeetingFactory meetingFactory) {
        this.modifyMeetingDataAccessObject = modifyMeetingDataAccessObject;
        this.modifyMeetingGmailAccessObject = modifyMeetingGmailAccessObject;
        this.modifyMeetingPresenter = modifyMeetingPresenter;
        this.meetingFactory = meetingFactory;
    }

    /**
     * Execution method that modifys the meeting.
     * Determines if the meeting can be modifyd by checking if the same meetingName already exists and whether
     * all invited participants are part of the project.
     * If all requirements are met, it modifys the new meeting, saves it to the firebase, and send a notification
     * email to the participants.
     *
     * @param modifyMeetingInputData required information from ModifyMeetingInputData class that will be used to
     *                               determine if the meeting can be modifyd  by the
     *                               ModifyMeetingInteractor class
     */
    @Override
    public void execute(ModifyMeetingInputData modifyMeetingInputData) throws ExecutionException, InterruptedException{
        String meetingName = modifyMeetingInputData.getMeetingName();
        ArrayList<String> participantEmail = modifyMeetingInputData.getParticipantEmail();
        String fromEmail = modifyMeetingInputData.getParticipantEmail().get(0);
        String meetingDate = modifyMeetingInputData.getMeetingDate();
        String startTime = modifyMeetingInputData.getStartTime();
        String endTime = modifyMeetingInputData.getEndTime();
        String projectName = modifyMeetingInputData.getProjectName();
        System.out.println("reached modify meeting interactor");

        if (!modifyMeetingDataAccessObject.meetingNameExists(projectName, meetingName)) {
            modifyMeetingPresenter.prepareFailView("Meeting does not exist");
        } else if (!modifyMeetingDataAccessObject.memberExists(projectName, participantEmail)) {
            modifyMeetingPresenter.prepareFailView("Member does not exist");
        } else {
            Meeting newMeeting = meetingFactory.create(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);
            modifyMeetingDataAccessObject.saveMeeting(newMeeting);

            for (String toEmail: participantEmail) {
                try {
                    modifyMeetingGmailAccessObject.sendMeetingModificationEmail(toEmail, fromEmail, meetingName);
                } catch (IOException | MessagingException e) {
                    throw new RuntimeException(e);
                }
            }

            ModifyMeetingOutputData modifyMeetingOutputData = new ModifyMeetingOutputData(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);
            modifyMeetingPresenter.prepareSuccessView(modifyMeetingOutputData);
        }
    }
}