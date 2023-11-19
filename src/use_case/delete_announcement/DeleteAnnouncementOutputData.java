package use_case.delete_announcement;

import entity.Announcement;

public class DeleteAnnouncementOutputData {
    private Announcement announcement;

    private String deleteTime;

    private boolean useCaseFailed;

    public DeleteAnnouncementOutputData(Announcement announcement, String deleteTme, boolean useCaseFailed) {
        this.announcement = announcement;
        this.deleteTime = deleteTime;
        this.useCaseFailed = useCaseFailed;
    }

    public Announcement outputAnnouncement(){
        return announcement;
    }

    public void setDeleteTime(String deleteTime){this.deleteTime = deleteTime;}
}
