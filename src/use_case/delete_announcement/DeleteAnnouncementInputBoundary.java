package use_case.delete_announcement;

public interface DeleteAnnouncementInputBoundary {
    void execute(String announcementID, String currentUser);
}
