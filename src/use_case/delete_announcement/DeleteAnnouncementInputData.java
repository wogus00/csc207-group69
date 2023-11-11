package use_case.delete_announcement;

public class DeleteAnnouncementInputData {
    private String announcementId;
    private String userId;
    public DeleteAnnouncementInputData(String announcementId, String userId){
        this.announcementId = announcementId;
        this.userId = userId;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
