package use_case.delete_announcement;

/**
 * Data class representing the input data required for deleting an announcement.
 * This class encapsulates the information necessary to identify which announcement is to be deleted.
 */
public class DeleteAnnouncementInputData {
    private String announcementId;
    private String userId;

    /**
     * Constructs a new DeleteAnnouncementInputData with the specified announcement ID and user ID.
     *
     * @param announcementId The unique identifier of the announcement to be deleted.
     * @param userId         The user ID of the user performing the deletion.
     */
    public DeleteAnnouncementInputData(String announcementId, String userId){
        this.announcementId = announcementId;
        this.userId = userId;
    }


    /**
     * Retrieves the ID of the announcement to be deleted.
     *
     * @return The announcement ID.
     */
    public String getAnnouncementId() {
        return announcementId;
    }

    /**
     * Sets the ID of the announcement to be deleted.
     *
     * @param announcementId The announcement ID to set.
     */
    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    /**
     * Retrieves the ID of the user performing the deletion.
     *
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user performing the deletion.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
