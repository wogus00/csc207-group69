package use_case.create_project;

import com.google.api.services.gmail.model.Message;

import javax.mail.MessagingException;
import java.io.IOException;

public interface CreateProjectGmailDataAccessInterface {
    Message sendProjectCreationEmail(String fromEmail, String toEmail, String projectName) throws IOException, MessagingException;
}
