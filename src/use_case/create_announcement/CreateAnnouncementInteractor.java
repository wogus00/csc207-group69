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
        Announcement announcement = announcementFactory.create(
                createAnnouncementInputData.getAnnouncementTitle(),
                createAnnouncementInputData.getMessage(),
                now,
                createAnnouncementInputData.getAuthor(),
                createAnnouncementInputData.getAnnouncementId());

        // Save the announcement to Firebase
        try {
            createAnnouncementDataAccessObject.save(announcement);
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
            createAnnouncementPresenter.prepareFailView(createAnnouncementOutputData, e.getMessage());
        }
    }


}
