package use_case.delete_announcement;

import entity.Announcement;

/**
 * Data class representing the output from the delete announcement use case.
 * This class encapsulates information about the announcement after its deletion attempt,
 * including whether the deletion was successful.
 */
public class DeleteAnnouncementOutputData {
    private Announcement announcement;

    private String deleteTime;

    private boolean isDeleted;

    /**
     * Constructs a new DeleteAnnouncementOutputData with given details.
     *
     * @param announcement The announcement being deleted.
     * @param deleteTime   The time at which the deletion was attempted.
     * @param isDeleted    Whether the deletion was successful.
     */
    public DeleteAnnouncementOutputData(Announcement announcement, String deleteTime, boolean isDeleted) {
        this.announcement = announcement;
        this.isDeleted = isDeleted;
        this.deleteTime = deleteTime;

    }

    /**
     * Sets the announcement that is subject to deletion.
     *
     * @param announcement The announcement being deleted.
     */
    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    /**
     * Sets the time at which the deletion was attempted.
     *
     * @param deleteTime The deletion time.
     */
    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * Sets the deletion status of the announcement.
     *
     * @param deleted True if the announcement was successfully deleted, false otherwise.
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Gets the announcement involved in the deletion.
     *
     * @return The announcement.
     */
    public Announcement getAnnouncement() {
        return announcement;
    }

    /**
     * Gets the time at which the deletion was attempted.
     *
     * @return The deletion time.
     */
    public String getDeleteTime() {
        return deleteTime;
    }

    /**
     * Gets the deletion status of the announcement.
     *
     * @return True if the announcement was successfully deleted, false otherwise.
     */
    public boolean getIsDeleted() {
        return isDeleted;
    }
}
