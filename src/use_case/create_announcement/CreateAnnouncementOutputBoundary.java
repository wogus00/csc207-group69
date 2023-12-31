package use_case.create_announcement;

public interface CreateAnnouncementOutputBoundary {
    void prepareSuccessView(CreateAnnouncementOutputData announcement);

    void prepareFailView(String error);
}
