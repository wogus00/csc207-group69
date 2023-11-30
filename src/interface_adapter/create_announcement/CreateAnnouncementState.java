package interface_adapter.create_announcement;

/**
 * Represents the state of the announcement creation process.
 * This state object stores data related to the creation of an announcement
 * and any errors that may have occurred during the process.
 */
public class CreateAnnouncementState {
    private String announcementTitle = "";

    private String message = "";

    private String announcementTitleError = null;
    private String response = null;

    private String author = "";

    private String id = "";
    private String project = "";

    /**
     * Constructs a new state by copying data from another state instance.
     *
     * @param copy The state instance to copy data from.
     */
    public CreateAnnouncementState(CreateAnnouncementState copy) {
        announcementTitle = copy.announcementTitle;
        message = copy.message;
        announcementTitleError = copy.announcementTitleError;
        author = copy.author;
        id = copy.id;
        project = copy.project;
        response = copy.response;
    }

    /**
     * Default constructor for creating an initial blank state.
     */
    public CreateAnnouncementState(){}

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
     * Gets the error of the announcement.
     *
     * @return the error of the announcement.
     */
    public String getAnnouncementTitleError(){return announcementTitleError;}

    /**
     * Gets the author of the announcement.
     *
     * @return the author of the announcement.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the name of project that the announcement belongs to.
     *
     * @return the name of project.
     */
    public String getProject() {
        return project;
    }

    /**
     * Gets the response of create announcement action;
     *
     * @return the response of action.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the title of the announcement.
     *
     * @param announcementTitle the title to set.
     */
    public void setAnnouncementTitle(String announcementTitle){this.announcementTitle = announcementTitle;}

    /**
     * Sets the message of the announcement.
     *
     * @param message the message to set.
     */
    public void setMessage(String message){this.message = message;}

    /**
     * Sets the error of the announcement.
     *
     * @param announcementTitleError the error to set.
     */
    public void setAnnouncementTitleError(String announcementTitleError){this.announcementTitleError = announcementTitleError;}

    /**
     * Sets the author of the announcement.
     *
     * @param author the author to set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the id of the announcement.
     *
     * @return the id of the announcement.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the announcement.
     *
     * @param id the id to set.
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Sets the name of the project that the announcement belongs to.
     *
     * @param project the name of the project.
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * Sets the response of create announcement action;
     *
     * @param response the response of action.
     */
    public void setResponse(String response) {
        this.response = response;
    }

}
