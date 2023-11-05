package entity;

import java.time.LocalDateTime;

public interface AnnouncementFactory {
    Announcement create(String announcementTitle, String message, LocalDateTime ltd);
}
