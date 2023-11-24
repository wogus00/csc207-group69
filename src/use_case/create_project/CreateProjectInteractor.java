package use_case.create_project;

import entity.Project;
import entity.ProjectFactory;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.ArrayList;

public class CreateProjectInteractor implements CreateProjectInputBoundary {
    final CreateProjectDataAccessInterface createProjectDataAccessObject;

    final CreateProjectGmailDataAccessInterface gmailDataAccessObject;
    final CreateProjectOutputBoundary createProjectPresenter;
    final ProjectFactory projectFactory;

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

        if (createProjectDataAccessObject.existsByName(projectName)) {
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