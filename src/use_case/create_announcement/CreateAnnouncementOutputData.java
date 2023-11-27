package use_case.create_announcement;

/**
 * Data class representing the output from the create announcement use case.
 * This class encapsulates the information about the announcement after its creation attempt,
 * including whether the use case succeeded or failed.
 */
public class CreateAnnouncementOutputData {

    private final String announcementTitle;

    private final String message;

    private String creationTime;

    private boolean useCaseFailed;

    private String author;

    private String announcementID;

    /**
     * Constructs a new CreateAnnouncementOutputData with given details.
     *
     * @param announcementTitle The title of the announcement.
     * @param message           The message of the announcement.
     * @param creationTime      The creation time of the announcement.
     * @param useCaseFailed     Whether the use case failed or succeeded.
     * @param author            The author of the announcement.
     * @param announcementID    The unique identifier of the announcement.
     */
    public CreateAnnouncementOutputData(String announcementTitle, String message, String creationTime, boolean useCaseFailed, String author, String announcementID) {
        this.announcementTitle = announcementTitle;
        this.message = message;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
        this.author = author;
        this.announcementID = announcementID;
    }

    /**
     * Gets the title of the announcement.
     *
     * @return the title of the announcement.
     */
    public String getAnnouncementTitle(){return announcementTitle;}

    /**
     * Gets the message of the announcement.
     *
     * @return the message of the announcement.
     */
    public String getMessage(){return message;}

    /**
     * Gets the creationTime of the announcement.
     *
     * @return the creationTime of the announcement.
     */
    public String getCreationTime(){return creationTime;}


//    public void setCreationTime(String creationTime){this.creationTime = creationTime;}

    /**
     * Gets the author of the announcement.
     *
     * @return the author of the announcement.
     */
    public String getAuthor(){return author;}

    /**
     * Gets the author id of the announcement.
     *
     * @return the author id of the announcement.
     */
    public String getAnnouncementID(){return announcementID;}

}
