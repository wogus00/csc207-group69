package use_case.create_project;

import javax.mail.internet.AddressException;
import java.io.IOException;

public interface CreateProjectInputBoundary {
    void execute(CreateProjectInputData createProjectInputData) throws IOException, AddressException;
}
