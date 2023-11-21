package entity;

import java.time.LocalDateTime;

/**
 * Represents an announcement with a title, message, timestamp, author, and identifier.
 * It encapsulates the data for an announcement in the system.
 */
public class CommonAnnouncement implements Announcement {

    private final String announcementTitle;
    private final String message;
    private final LocalDateTime creationTime;
    private final String author;
    private final String id;

    /**
     * Constructs a new CommonAnnouncement with the specified details.
     *
     * @param announcementTitle the title of the announcement
     * @param message           the message or content of the announcement
     * @param creationTime      the time at which the announcement was created
     * @param author            the author of the announcement
     * @param id                the unique identifier for the announcement
     */
    public CommonAnnouncement(String announcementTitle, String message, LocalDateTime creationTime, String author, String id) {
        this.announcementTitle = announcementTitle;
        this.message = message;
        this.creationTime = creationTime;
        this.author = author;
        this.id = id;
    }

    /**
     * Retrieves the title of the announcement.
     *
     * @return the announcement title
     */
    @Override
    public String getAnnouncementTitle() { return announcementTitle; }

    /**
     * Retrieves the message of the announcement.
     *
     * @return the announcement message
     */
    @Override
    public String getMessage() { return message; }

    /**
     * Retrieves the creation time of the announcement.
     *
     * @return the time at which the announcement was created
     */
    @Override
    public LocalDateTime getCreationTime() { return creationTime; }

    /**
     * Retrieves the author of the announcement.
     *
     * @return the author's name
     */
    @Override
    public String getAuthor() { return author; }

    /**
     * Retrieves the unique identifier of the announcement.
     *
     * @return the announcement identifier
     */
    @Override
    public String getId() { return id; }
}
