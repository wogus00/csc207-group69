package interface_adapter.create_announcement;

import use_case.create_announcement.CreateAnnouncementInputBoundary;
import use_case.create_announcement.CreateAnnouncementInputData;

public class CreateAnnouncementController {
    final CreateAnnouncementInputBoundary createAnnouncementUseCaseInteractor;

    public CreateAnnouncementController(CreateAnnouncementInputBoundary createAnnouncementUseCaseInteractor) {
        this.createAnnouncementUseCaseInteractor = createAnnouncementUseCaseInteractor;
    }

    public void execute(String announcementTitle, String message, String author) {
        CreateAnnouncementInputData createAnnouncementInputData = new CreateAnnouncementInputData(announcementTitle, message, author);

        createAnnouncementUseCaseInteractor.execute(createAnnouncementInputData);
    }
}
