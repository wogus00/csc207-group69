package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Announcement {
    String getAnnouncementTitle();

    String getMessage();

    String getAuthor();

    LocalDateTime getCreationTime();


    String getId();

}
