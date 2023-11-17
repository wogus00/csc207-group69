package use_case.delete_announcement;

import entity.Announcement;
import entity.CommonAnnouncement;
import interface_adapter.delete_announcement.DeleteAnnouncementPresenter;

import java.time.LocalDateTime;

public class DeleteAnnouncementInteractor implements DeleteAnnouncementInputBoundary {
    final DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject;

    private DeleteAnnouncementDataAccessInterface dataAccess;
    private DeleteAnnouncementPresenter presenter;
    final DeleteAnnouncementOutputBoundary deleteAnnouncementPresenter;

    public DeleteAnnouncementInteractor(DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject,
                                        DeleteAnnouncementOutputBoundary deleteAnnouncementOutputBoundary) {
        this.deleteAnnouncementDataAccessObject = deleteAnnouncementDataAccessObject;
        this.deleteAnnouncementPresenter = deleteAnnouncementOutputBoundary;
    }

    @Override
    public void execute(String announcementId, String currentUser) {
        try {
            CommonAnnouncement announcement = deleteAnnouncementDataAccessObject.getAnnouncementById(announcementId);

            if (announcement == null) {
                // Announcement not found
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
