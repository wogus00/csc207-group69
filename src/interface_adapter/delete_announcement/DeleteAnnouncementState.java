package interface_adapter.delete_announcement;

import entity.Announcement;

/**
 * Represents the state of the announcement deletion process.
 * This state object stores data related to the announcement being deleted and any errors that may have occurred during the process.
 */
public class DeleteAnnouncementState {
    private Announcement announcement;

    private String announcementError = null;

    /**
     * Constructs a new state by copying data from another state instance.
     *
     * @param copy The state instance to copy data from.
     */
    public DeleteAnnouncementState(DeleteAnnouncementState copy){
        announcement = copy.announcement;
        announcementError = copy.announcementError;
    }

    /**
     * Default constructor for creating an initial blank state.
     */
    public DeleteAnnouncementState(){

    }

    /**
     * Gets the error message associated with the announcement deletion process.
     *
     * @return the error message.
     */
    public String getAnnouncementError(){return announcementError;}

    /**
     * Gets the announcement.
     *
     * @return announcement.
     */
    public Announcement getAnnouncement() {
        return announcement;
    }

    /**
     * Sets the announcement to be deleted.
     *
     * @param announcement The announcement to set.
     */
    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    /**
     * Sets  announcement error.
     *
     * @param announcementError the announcementError to set.
     */
    public void setAnnouncementError(String announcementError) {
        this.announcementError = announcementError;
    }

    /**
     * Provides a string representation of the state, primarily the announcement title.
     *
     * @return The title of the announcement in the state.
     */
    @Override
    public String toString(){
        return announcement.getAnnouncementTitle();
    }
}
