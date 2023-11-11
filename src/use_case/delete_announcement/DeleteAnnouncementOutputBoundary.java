package use_case.delete_announcement;

public interface DeleteAnnouncementOutputBoundary {
    void prepareSuccessView(DeleteAnnouncementOutputData deleteAnnouncementOutputData);

    void prepareFailView(String error);
}
