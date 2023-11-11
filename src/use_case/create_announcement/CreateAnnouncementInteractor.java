package use_case.create_announcement;

import entity.Announcement;
import entity.AnnouncementFactory;
import interface_adapter.create_announcement.CreateAnnouncementPresenter;

import java.time.LocalDateTime;

public class CreateAnnouncementInteractor implements CreateAnnouncementInputBoundary {
    final CreateAnnouncementDataAccessInterface createAnnouncementDataAccessObject;

    final CreateAnnouncementOutputBoundary createAnnouncementPresenter;

    final AnnouncementFactory announcementFactory;
    public CreateAnnouncementInteractor(CreateAnnouncementDataAccessInterface createAnnouncementDataAccessObject,
                                        CreateAnnouncementOutputBoundary createAnnouncementOutputBoundary,
                                        AnnouncementFactory announcementFactory) {
        this.createAnnouncementDataAccessObject = createAnnouncementDataAccessObject;
        this.createAnnouncementPresenter = createAnnouncementOutputBoundary;
        this.announcementFactory = announcementFactory;
    }

    @Override
    public void execute(CreateAnnouncementInputData createAnnouncementInputData) {
        LocalDateTime now = LocalDateTime.now();
        Announcement announcement = announcementFactory.create(createAnnouncementInputData.getAnnouncementTitle(), createAnnouncementInputData.getMessage(), now, createAnnouncementInputData.getAuthor());

        CreateAnnouncementOutputData createAnnouncementOutputData = new CreateAnnouncementOutputData(announcement.getAnnouncementTitle(), announcement.getMessage(), now.toString(), false, announcement.getAuthor());
        createAnnouncementPresenter.prepareSuccessView(createAnnouncementOutputData);
    }

}
