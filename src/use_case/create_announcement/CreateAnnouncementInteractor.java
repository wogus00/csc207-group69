package use_case.create_announcement;

import entity.Announcement;
import entity.AnnouncementFactory;
import entity.CommonAnnouncement;
import entity.Project;
import interface_adapter.create_announcement.CreateAnnouncementPresenter;
import use_case.create_project.CreateProjectGmailDataAccessInterface;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interactor for handling the creation of announcements.
 * This class is responsible for executing the business logic associated with creating an announcement.
 * It interacts with the data access object to save announcements and communicates with the presenter
 * to update the view based on the outcome of the announcement creation process.
 */
public class CreateAnnouncementInteractor implements CreateAnnouncementInputBoundary {
    final CreateAnnouncementDataAccessInterface createAnnouncementDataAccessObject;

    final CreateAnnouncementOutputBoundary createAnnouncementPresenter;

    final AnnouncementFactory announcementFactory;

    final CreateAnnouncementGmailDataAccessInterface gmailDataAccessObject;

    /**
     * Constructs a CreateAnnouncementInteractor with the necessary dependencies for announcement creation.
     *
     * @param createAnnouncementDataAccessObject The data access object for saving announcements.
     * @param createAnnouncementOutputBoundary   The presenter for updating the view based on the outcome.
     * @param announcementFactory                The factory for creating announcement entities.
     */
    public CreateAnnouncementInteractor(CreateAnnouncementDataAccessInterface createAnnouncementDataAccessObject,
                                        CreateAnnouncementOutputBoundary createAnnouncementOutputBoundary,
                                        AnnouncementFactory announcementFactory,
                                        CreateAnnouncementGmailDataAccessInterface gmailDataAccessObject) {
        this.createAnnouncementDataAccessObject = createAnnouncementDataAccessObject;
        this.createAnnouncementPresenter = createAnnouncementOutputBoundary;
        this.announcementFactory = announcementFactory;
        this.gmailDataAccessObject = gmailDataAccessObject;
    }

    /**
     * Executes the announcement creation process.
     * It creates an announcement entity, attempts to save it, and then informs the presenter
     * to update the view based on whether the save operation was successful or not.
     *
     * @param createAnnouncementInputData The input data for creating an announcement.
     */


    @Override
    public void execute(CreateAnnouncementInputData createAnnouncementInputData) {
        LocalDateTime now = LocalDateTime.now();
        Announcement announcement = announcementFactory.create(
                createAnnouncementInputData.getAnnouncementTitle(),
                createAnnouncementInputData.getMessage(),
                now,
                createAnnouncementInputData.getAuthor(),
                createAnnouncementInputData.getAnnouncementId());

        try {
            createAnnouncementDataAccessObject.save(createAnnouncementInputData.getProjectName(), announcement);
            CreateAnnouncementOutputData createAnnouncementOutputData = new CreateAnnouncementOutputData(
                    announcement.getAnnouncementTitle(),
                    announcement.getMessage(),
                    now.toString(),
                    true,
                    announcement.getAuthor(),
                    announcement.getId());
            createAnnouncementPresenter.prepareSuccessView(createAnnouncementOutputData);
            ArrayList<String> membersList = createAnnouncementDataAccessObject.getMembersEmails(createAnnouncementInputData.getProjectName());
            for (String email: membersList) {
                try {
                    gmailDataAccessObject.sendAnnouncementCreationEmail(announcement.getAuthor(), email, announcement.getAnnouncementTitle());
                } catch (MessagingException | IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (Exception e) {
            // Handle any exceptions, e.g., database connection failure or email sending failure
            CreateAnnouncementOutputData createAnnouncementOutputData = new CreateAnnouncementOutputData(
                    announcement.getAnnouncementTitle(),
                    announcement.getMessage(),
                    now.toString(),
                    false,  // Indicating that the save operation failed
                    announcement.getAuthor(),
                    announcement.getId());
            createAnnouncementPresenter.prepareFailView(e.getMessage());
        }
    }




}
