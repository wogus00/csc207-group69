package use_case.complete_task;

import com.google.api.services.gmail.model.Message;

public interface CompleteTaskGmailDataAccessInterface {
    Message sendTaskCompletionEmail(String fromEmail, String toEmail, String taskName);
}
