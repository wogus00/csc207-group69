package use_case.create_announcement;
import com.google.api.services.gmail.model.Message;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;

public interface CreateAnnouncementGmailDataAccessInterface {
    Message sendAnnouncementCreationEmail(String author, String toEmail, String announcementTitle) throws IOException, MessagingException;

}
