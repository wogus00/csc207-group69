package use_case.create_project;

import java.io.IOException;

public interface CreateProjectGmailDataAccessInterface {
    void sendProjectCreationEmail(String leaderEmail, String email, String projectName) throws IOException;
}
