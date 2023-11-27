package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;


public interface AnnouncementFactory {
    Announcement create(String announcementTitle,
                        String message,
                        LocalDateTime ltd,
                        String author,
                        String id,
                        ArrayList<String> emailsSent);
}
