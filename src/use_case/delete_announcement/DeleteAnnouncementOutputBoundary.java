package use_case.delete_announcement;

public interface DeleteAnnouncementOutputBoundary {

    void prepareNotFoundView(String announcementId);

    void prepareUnauthorizedView(String announcementId);

    void prepareErrorView(String announcementId, String errorMessage);

    void prepareFailureView(DeleteAnnouncementOutputData deleteAnnouncementOutputData);

    void prepareSuccessView(DeleteAnnouncementOutputData deleteAnnouncementOutputData);

    void prepareFailView(String error);


}
