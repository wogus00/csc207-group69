package use_case.delete_announcement;

import entity.Announcement;
import entity.CommonAnnouncement;
import interface_adapter.delete_announcement.DeleteAnnouncementPresenter;

import java.time.LocalDateTime;

/**
 * Interactor for handling the deletion of announcements.
 * This class is responsible for executing the business logic associated with deleting an announcement.
 * It interacts with the data access object to delete announcements and communicates with the presenter
 * to update the view based on the outcome of the deletion process.
 */
public class DeleteAnnouncementInteractor implements DeleteAnnouncementInputBoundary {
    final DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject;

    private DeleteAnnouncementDataAccessInterface dataAccess;
    private DeleteAnnouncementPresenter presenter;
    final DeleteAnnouncementOutputBoundary deleteAnnouncementPresenter;

    /**
     * Constructs a DeleteAnnouncementInteractor with the specified data access object and output boundary.
     *
     * @param deleteAnnouncementDataAccessObject The data access object for deleting announcements.
     * @param deleteAnnouncementOutputBoundary   The output boundary to present the results of the deletion process.
     */
    public DeleteAnnouncementInteractor(DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject,
                                        DeleteAnnouncementOutputBoundary deleteAnnouncementOutputBoundary) {
        this.deleteAnnouncementDataAccessObject = deleteAnnouncementDataAccessObject;
        this.deleteAnnouncementPresenter = deleteAnnouncementOutputBoundary;
    }

    /**
     * Executes the deletion process for an announcement.
     * Determines if the announcement can be deleted based on the provided criteria and performs the deletion if possible.
     *
     * @param announcementId The unique identifier of the announcement to be deleted.
     * @param currentUser    The user attempting to delete the announcement.
     */
    @Override
    public void execute(String announcementId, String currentUser) {
        try {
            CommonAnnouncement announcement = deleteAnnouncementDataAccessObject.getAnnouncementById(announcementId);

            if (announcement == null) {
                // Announcement not found
                System.out.println("reached");
                deleteAnnouncementPresenter.prepareNotFoundView(announcementId);
                return;
            }

            if (!announcement.getAuthor().equals(currentUser)) {
                // The user trying to delete is not the author
                deleteAnnouncementPresenter.prepareUnauthorizedView(announcementId);
                return;
            }

            LocalDateTime now = LocalDateTime.now();

            // Proceed with deletion
            boolean isDeleted = deleteAnnouncementDataAccessObject.deleteAnnouncement(announcementId);
            DeleteAnnouncementOutputData deleteAnnouncementOutputData = new DeleteAnnouncementOutputData(announcement, now.toString(), isDeleted);
            if (isDeleted) {
                deleteAnnouncementPresenter.prepareSuccessView(deleteAnnouncementOutputData);
            } else {
                deleteAnnouncementPresenter.prepareFailureView(deleteAnnouncementOutputData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            deleteAnnouncementPresenter.prepareErrorView(announcementId, e.getMessage());
        }
    }
}
