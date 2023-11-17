package use_case.delete_announcement;

import entity.Announcement;

public class DeleteAnnouncementOutputData {
    private Announcement announcement;

    private String deleteTime;

    private boolean isDeleted;

    public DeleteAnnouncementOutputData(Announcement announcement, String deleteTme, boolean isDeleted) {
        this.announcement = announcement;
        this.deleteTime = deleteTime;
        this.isDeleted = isDeleted;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }
}
