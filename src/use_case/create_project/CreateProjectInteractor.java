package use_case.create_project;

import entity.Project;
import entity.ProjectFactory;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Public interactor class for "Create Project" use case.
 * It is responsible for executing the  business logic associated with creating the project.
 * Interacts with FirebaseDataAccessObject to create the project and save it to the database, GmailDataAccessObject to send emails, and communicates with the presenter
 * to update the view based on completion of the use case.
 */
public class CreateProjectInteractor implements CreateProjectInputBoundary {
    final CreateProjectDataAccessInterface createProjectDataAccessObject;

    final CreateProjectGmailDataAccessInterface gmailDataAccessObject;
    final CreateProjectOutputBoundary createProjectPresenter;
    final ProjectFactory projectFactory;

    /**
     * @param projectDataAccessInterface Interface implemented by FirebaseDataAccessObject responsible for communicating with Google Firebase.
     * @param gmailDataAccessInterface Interface implemented by GmailDataAccessObject responsible for communicating with Gmail API
     * @param createProjectOutputBoundary Output Boundary to present the result of the creating project
     * @param projectFactory ProjectFactory entity responsible for creating the project.
     */
    public CreateProjectInteractor(CreateProjectDataAccessInterface projectDataAccessInterface,
                                   CreateProjectGmailDataAccessInterface gmailDataAccessInterface,
                                   CreateProjectOutputBoundary createProjectOutputBoundary,
                                   ProjectFactory projectFactory) {
        this.createProjectDataAccessObject = projectDataAccessInterface;
        this.gmailDataAccessObject = gmailDataAccessInterface;
        this.createProjectPresenter = createProjectOutputBoundary;
        this.projectFactory = projectFactory;
    }


    @Override
    public void execute(CreateProjectInputData createProjectInputData) throws IOException, AddressException {
        String projectName = createProjectInputData.getProjectName();
        String leaderEmail = createProjectInputData.getLeaderEmail();
        ArrayList<String> memberEmails = createProjectInputData.getMemberEmails();

        if (!createProjectDataAccessObject.existsByName(projectName)) {
            createProjectPresenter.prepareFailView("Project already exists.");
        } else {
            Project project = projectFactory.create(projectName, leaderEmail, memberEmails);
            createProjectDataAccessObject.save(project);


            for (String email: memberEmails) {
                try {
                    gmailDataAccessObject.sendProjectCreationEmail(leaderEmail, email, projectName);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }

            CreateProjectOutputData createProjectOutputData = new CreateProjectOutputData(project.getProjectName(), project.getLeaderEmail(), project.getMemberEmails(), true);
            createProjectPresenter.prepareSuccessView(createProjectOutputData);
            }
        }


}