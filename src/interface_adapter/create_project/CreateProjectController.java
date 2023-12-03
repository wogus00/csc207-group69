package interface_adapter.create_project;

import use_case.create_project.CreateProjectInputBoundary;
import use_case.create_project.CreateProjectInputData;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class is responsible for creating a project.
 * This class takes inputs from the user about the details of the project and
 * passes it to the Create Project use case interactor.
 *
 */
public class CreateProjectController {
    final CreateProjectInputBoundary createProjectUseCaseInteractor;
    /**
     * Constructs a CreateProjectController with the Create Project use case interactor.
     *
     * @param createProjectUseCaseInteractor Interactor class that is responsible for
     *                                       the business logic of creating project.
     */

    public CreateProjectController(CreateProjectInputBoundary createProjectUseCaseInteractor) {
        this.createProjectUseCaseInteractor = createProjectUseCaseInteractor;
    }

    /**
     * Executes the process of creating a new meeting.
     *
     * @param projectName                The name of the project that this meeting is about.
     * @param leaderEmail                The email of the leader.
     * @param memberEmails               The emails of all other members of the project.
     */
    public void execute(String projectName, String leaderEmail, ArrayList<String> memberEmails) throws IOException, AddressException {
        CreateProjectInputData createProjectInputData = new CreateProjectInputData(projectName, leaderEmail, memberEmails);


        createProjectUseCaseInteractor.execute(createProjectInputData);
    }
}
