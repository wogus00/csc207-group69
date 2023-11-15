package use_case.create_meeting;

import com.google.api.services.gmail.model.Message;

import javax.mail.MessagingException;
import java.io.IOException;

public interface CreateMeetingGmailDataAccessInterface {
    Message sendMeetingCreationEmail(String email, String meetingName) throws MessagingException, IOException;
}

