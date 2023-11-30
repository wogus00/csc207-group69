package use_case.create_announcement;

import entity.Announcement;
import entity.AnnouncementFactory;
import interface_adapter.create_announcement.CreateAnnouncementPresenter;

import java.time.LocalDateTime;

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

    /**
     * Constructs a CreateAnnouncementInteractor with the necessary dependencies for announcement creation.
     *
     * @param createAnnouncementDataAccessObject The data access object for saving announcements.
     * @param createAnnouncementOutputBoundary   The presenter for updating the view based on the outcome.
     * @param announcementFactory                The factory for creating announcement entities.
     */
    public CreateAnnouncementInteractor(CreateAnnouncementDataAccessInterface createAnnouncementDataAccessObject,
                                        CreateAnnouncementOutputBoundary createAnnouncementOutputBoundary,
                                        AnnouncementFactory announcementFactory) {
        this.createAnnouncementDataAccessObject = createAnnouncementDataAccessObject;
        this.createAnnouncementPresenter = createAnnouncementOutputBoundary;
        this.announcementFactory = announcementFactory;
    }

    /**
     * Executes the announcement creation process.
     * It creates an announcement entity, attempts to save it, and then informs the presenter
     * to update the view based on whether the save operation was successful or not.
     *
     * @param createAnnouncementInputData The input data for creating an announcement.
     */


    // TODO send announcements to selected gmail users. Look at the CreateTask for the implementation
    // TODO so, i also have to edit gmaildataaccess

    @Override
    public void execute(CreateAnnouncementInputData createAnnouncementInputData) {
        LocalDateTime now = LocalDateTime.now();
        Announcement announcement = announcementFactory.create(
                createAnnouncementInputData.getAnnouncementTitle(),
                createAnnouncementInputData.getMessage(),
                now,
                createAnnouncementInputData.getAuthor(),
                createAnnouncementInputData.getAnnouncementId());

        // Save the announcement to Firebase
        try {
            createAnnouncementDataAccessObject.save(createAnnouncementInputData.getProjectName(), announcement);
            CreateAnnouncementOutputData createAnnouncementOutputData = new CreateAnnouncementOutputData(
                    announcement.getAnnouncementTitle(),
                    announcement.getMessage(),
                    now.toString(),
                    true,  // Assuming the save operation is successful
                    announcement.getAuthor(),
                    announcement.getId());
            createAnnouncementPresenter.prepareSuccessView(createAnnouncementOutputData);
        } catch (Exception e) {
            // Handle any exceptions, e.g., database connection failure
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
