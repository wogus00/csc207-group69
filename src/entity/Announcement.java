package entity;

import java.time.LocalDateTime;

public interface Announcement {
    String getAnnouncementTitle();

    String getMessage();

    String getAuthor();

    LocalDateTime getCreationTime();


    String getId();
}
