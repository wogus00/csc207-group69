package entity;

import java.time.LocalDateTime;

public interface Announcement {
    String getAnnouncementTitle();

    String getMessage();

    LocalDateTime getCreationTime();
}
