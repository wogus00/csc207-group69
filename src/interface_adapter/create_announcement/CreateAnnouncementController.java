package interface_adapter.create_announcement;

import use_case.create_announcement.CreateAnnouncementInputBoundary;
import use_case.create_announcement.CreateAnnouncementInputData;

import javax.mail.internet.AddressException;
import java.io.IOException;

/**
 * Controller for handling the creation of announcements.
 * This class is responsible for taking input from the presentation layer,
 * creating the appropriate data object, and passing it to the use case interactor
 * for business logic execution.
 */
public class CreateAnnouncementController {
    private final CreateAnnouncementInputBoundary createAnnouncementUseCaseInteractor;

    /**
     * Constructs a CreateAnnouncementController with the specified announcement use case interactor.
     *
     * @param createAnnouncementUseCaseInteractor The interactor that encapsulates the business logic for creating announcements.
     */
    public CreateAnnouncementController(CreateAnnouncementInputBoundary createAnnouncementUseCaseInteractor) {
        this.createAnnouncementUseCaseInteractor = createAnnouncementUseCaseInteractor;
    }

    /**
     * Executes the process of creating a new announcement.
     *
     * @param announcementTitle The title of the announcement to be created.
     * @param message           The message of the announcement.
     * @param author            The author of the announcement.
     * @throws IOException       If there is a failure in the input/output operations.
     * @throws AddressException  If there is an error in the email address processing.
     */
    public void execute(String announcementTitle, String message, String author) throws IOException, AddressException {
        CreateAnnouncementInputData createAnnouncementInputData = new CreateAnnouncementInputData(announcementTitle, message, author);
        createAnnouncementUseCaseInteractor.execute(createAnnouncementInputData);
    }
}

