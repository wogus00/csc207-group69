package use_case.delete_announcement;

import entity.Announcement;

import java.time.LocalDateTime;

public class DeleteAnnouncementInteractor implements DeleteAnnouncementInputBoundary {
    final DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject;

    final DeleteAnnouncementOutputBoundary deleteAnnouncementPresenter;

    public DeleteAnnouncementInteractor(DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject,
                                        DeleteAnnouncementOutputBoundary deleteAnnouncementOutputBoundary) {
        this.deleteAnnouncementDataAccessObject = deleteAnnouncementDataAccessObject;
        this.deleteAnnouncementPresenter = deleteAnnouncementOutputBoundary;
    }

    @Override
    public void execute(String announcementTitle){
        LocalDateTime now = LocalDateTime.now();
        Announcement announcement = deleteAnnouncementDataAccessObject.clear(announcementTitle);
        DeleteAnnouncementOutputData deleteAnnouncementOutputData = new DeleteAnnouncementOutputData(announcement, now.toString(), false);

        deleteAnnouncementPresenter.prepareSuccessView(deleteAnnouncementOutputData);
    }
}
