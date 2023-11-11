package interface_adapter.delete_announcement;

import use_case.delete_announcement.DeleteAnnouncementInputBoundary;

public class DeleteAnnouncementController {
    final DeleteAnnouncementInputBoundary deleteAnnouncementInteractor;

    public DeleteAnnouncementController(DeleteAnnouncementInputBoundary deleteAnnouncementInteractor) {
        this.deleteAnnouncementInteractor = deleteAnnouncementInteractor;
    }

    public void execute(){deleteAnnouncementInteractor.execute();}
}
