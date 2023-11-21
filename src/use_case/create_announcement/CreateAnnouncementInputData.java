package use_case.create_announcement;

import java.util.UUID;

/**
 * Data class for holding input data necessary for creating an announcement.
 * This class encapsulates the title, message, author, and an auto-generated ID for an announcement.
 */
public class CreateAnnouncementInputData {

    final private String announcementTitle;

    final private String message;

    final private String author;

    final private String announcementId;

    /**
     * Constructs a new CreateAnnouncementInputData with the specified title, message, and author.
     * An announcement ID is automatically generated.
     *
     * @param announcementTitle The title of the announcement.
     * @param message           The message of the announcement.
     * @param author            The author of the announcement.
     */
    public CreateAnnouncementInputData(String announcementTitle, String message, String author) {
        this.announcementTitle = announcementTitle;
        this.message = message;
        this.author = author;
        this.announcementId = UUID.randomUUID().toString();
    }

    /**
     * Retrieves the title of the announcement.
     *
     * @return the announcement title.
     */
    String getAnnouncementTitle(){return announcementTitle;}

    /**
     * Retrieves the message of the announcement.
     *
     * @return the announcement message.
     */
    String getMessage(){return message;}

    /**
     * Retrieves the author of the announcement.
     *
     * @return the author's name.
     */
    String getAuthor(){return this.announcementId;}

    /**
     * Retrieves the unique identifier for the announcement.
     *
     * @return the announcement ID.
     */
    String getAnnouncementId(){return announcementId;}
}
