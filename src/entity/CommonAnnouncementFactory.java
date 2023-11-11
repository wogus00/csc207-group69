package entity;

import java.time.LocalDateTime;

public class CommonAnnouncementFactory implements AnnouncementFactory {
    @Override
    public Announcement create(String announcementTitle, String message, LocalDateTime ltd, String author){return new CommonAnnouncement(announcementTitle, message, ltd, author);}
}
