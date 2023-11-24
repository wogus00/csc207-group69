package use_case.create_task;

import com.google.api.services.gmail.model.Message;

import javax.mail.MessagingException;
import java.io.IOException;

public interface CreateTaskGmailDataAccessInterface {
    Message sendTaskCreationEmail(String supervisor, String email, String taskName) throws MessagingException, IOException;
}