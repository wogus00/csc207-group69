package use_case.modify_meeting;

import entity.Meeting;
import entity.MeetingFactory;
import use_case.create_meeting.CreateMeetingOutputData;
import use_case.modify_meeting.ModifyMeetingDataAccessInterface;
import use_case.modify_meeting.ModifyMeetingGmailDataAccessInterface;
import use_case.modify_meeting.ModifyMeetingOutputBoundary;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class ModifyMeetingInteractor implements ModifyMeetingInputBoundary {
    final ModifyMeetingDataAccessInterface modifyMeetingDataAccessObject;
    final ModifyMeetingGmailDataAccessInterface modifyMeetingGmailAccessObject;
    final ModifyMeetingOutputBoundary modifyMeetingPresenter;
    final MeetingFactory meetingFactory;
    public ModifyMeetingInteractor(ModifyMeetingDataAccessInterface userDataAccessObject, ModifyMeetingGmailDataAccessInterface gmailDataAccessObject, ModifyMeetingOutputBoundary modifyMeetingOutputBoundary, MeetingFactory meetingFactory) {
        this.modifyMeetingDataAccessObject = userDataAccessObject;
        this.modifyMeetingGmailAccessObject = gmailDataAccessObject;
        this.modifyMeetingPresenter = modifyMeetingOutputBoundary;
        this.meetingFactory = meetingFactory;
    }

    @Override
    public void execute(ModifyMeetingInputData modifyMeetingInputData) {
        String meetingName = modifyMeetingInputData.getMeetingName();
        ArrayList<String> participantEmail = modifyMeetingInputData.getParticipantEmail();
        String fromEmail = modifyMeetingInputData.getParticipantEmail().get(0);
        String meetingDate = modifyMeetingInputData.getMeetingDate();
        String startTime = modifyMeetingInputData.getStartTime();
        String endTime = modifyMeetingInputData.getEndTime();
        String projectName = modifyMeetingInputData.getProjectName();

        if (!modifyMeetingDataAccessObject.meetingNameExists(projectName, meetingName)) {
            modifyMeetingPresenter.prepareFailView("Meeting name is already taken");
        } else if (!modifyMeetingDataAccessObject.memberExists(projectName, participantEmail)) {
            modifyMeetingPresenter.prepareFailView("Member does not exist");
        } else {
            Meeting newMeeting = meetingFactory.create(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);
            modifyMeetingDataAccessObject.deleteOldMeeting(projectName, meetingName);
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
}}
