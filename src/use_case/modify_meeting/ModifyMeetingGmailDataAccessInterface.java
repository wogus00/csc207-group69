package use_case.modify_meeting;

import com.google.api.services.gmail.model.Message;

import javax.mail.MessagingException;
import java.io.IOException;
import data_access.GmailDataAccessObject;

public interface ModifyMeetingGmailDataAccessInterface {
    Message sendMeetingModificationEmail(String toEmail, String fromEmail, String meetingName) throws MessagingException, IOException;
}

