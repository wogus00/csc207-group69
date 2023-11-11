package interface_adapter.delete_announcement;

import entity.Announcement;

public class DeleteAnnouncementState {
    private Announcement announcement;

    private String announcementError = null;

    public DeleteAnnouncementState(DeleteAnnouncementState copy){
        announcement = copy.announcement;
        announcementError = copy.announcementError;
    }

    public DeleteAnnouncementState(){

    }

    public String getAnnouncementError(){return announcementError;}

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public void setAnnouncementError(String announcementError) {
        this.announcementError = announcementError;
    }

    @Override
    public String toString(){
        return announcement.getAnnouncementTitle();
    }
}
