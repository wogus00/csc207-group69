package entity;

import java.time.LocalDateTime;

/**
 * Factory for creating CommonAnnouncement instances.
 * This class provides a method to create new announcements with given parameters.
 */
public class CommonAnnouncementFactory implements AnnouncementFactory {

    /**
     * Creates a new CommonAnnouncement with the specified details.
     *
     * @param announcementTitle the title of the announcement
     * @param message           the message or content of the announcement
     * @param ltd               the LocalDateTime the announcement was created on
     * @param author            the author of the announcement
     * @param id                the unique identifier for the announcement
     * @return an instance of CommonAnnouncement
     */
    @Override
    public Announcement create(String announcementTitle, String message, LocalDateTime ltd, String author, String id) {
        return new CommonAnnouncement(announcementTitle, message, ltd, author, id);
    }
}

