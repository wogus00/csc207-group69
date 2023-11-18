package interface_adapter.create_announcement;

public class CreateAnnouncementState {
    private String announcementTitle = "";

    private String message = "";

    private String announcementTitleError = null;

    private String author = "";

    private String id = "";

    public CreateAnnouncementState(CreateAnnouncementState copy) {
        announcementTitle = copy.announcementTitle;
        message = copy.message;
        announcementTitleError = copy.announcementTitleError;
        author = copy.author;
        id = copy.id;
    }

    public CreateAnnouncementState(){}

    public String getAnnouncementTitle(){return announcementTitle;}

    public String getMessage(){return message;}

    public String getAnnouncementTitleError(){return announcementTitleError;}

    public String getAuthor() {
        return author;
    }

    public void setAnnouncementTitle(String announcementTitle){this.announcementTitle = announcementTitle;}

    public void setMessage(String message){this.message = message;}

    public void setAnnouncementTitleError(String announcementTitleError){this.announcementTitleError = announcementTitleError;}

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
