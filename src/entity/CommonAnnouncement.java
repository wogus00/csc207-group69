package entity;

import java.time.LocalDateTime;

public class CommonAnnouncement implements Announcement {
    private final String announcementTitle;

    private final String message;

    private final LocalDateTime creationTime;

    CommonAnnouncement(String announcementTitle, String message, LocalDateTime creationTime) {
        this.announcementTitle = announcementTitle;
        this.message = message;
        this.creationTime = creationTime;

    }

    @Override
    public String getAnnouncementTitle(){return announcementTitle;}

    @Override
    public String getMessage(){return message;}

    @Override
    public LocalDateTime getCreationTime(){return creationTime;}
}
