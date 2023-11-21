package interface_adapter.delete_announcement;

import use_case.delete_announcement.DeleteAnnouncementInputBoundary;

/**
 * Controller for handling the deletion of announcements.
 * This class is responsible for taking input regarding which announcement to delete
 * and delegating the deletion process to the appropriate interactor.
 */
public class DeleteAnnouncementController {

    final DeleteAnnouncementInputBoundary deleteAnnouncementInteractor;

    /**
     * Constructs a DeleteAnnouncementController with the specified deletion interactor.
     *
     * @param deleteAnnouncementInteractor The interactor responsible for the business logic of deleting announcements.
     */
    public DeleteAnnouncementController(DeleteAnnouncementInputBoundary deleteAnnouncementInteractor) {
        this.deleteAnnouncementInteractor = deleteAnnouncementInteractor;
    }

    /**
     * Executes the deletion process for an announcement.
     *
     * @param id                The unique identifier of the announcement to be deleted.
     * @param announcementTitle The title of the announcement to be deleted.
     */
    public void execute(String id, String announcementTitle){
        deleteAnnouncementInteractor.execute(id, announcementTitle);}
}
