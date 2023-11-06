package use_case.create_project;

import java.io.IOException;

public interface CreateProjectInputBoundary {
    void execute(CreateProjectInputData createProjectInputData) throws IOException;
}
