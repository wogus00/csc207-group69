package interface_adapter.create_announcement;

public class CreateAnnouncementState {
    private String announcementTitle = "";

    private String message = "";

    private String announcementTitleError = null;

    public CreateAnnouncementState(CreateAnnouncementState copy) {
        announcementTitle = copy.announcementTitle;
        message = copy.message;
        announcementTitleError = copy.announcementTitleError;
    }

    public CreateAnnouncementState(){}

    public String getAnnouncementTitle(){return announcementTitle;}

    public String getMessage(){return message;}

    public String getAnnouncementTitleError(){return announcementTitleError;}

    public void setAnnouncementTitle(String announcementTitle){this.announcementTitle = announcementTitle;}

    public void setMessage(String message){this.message = message;}

    public void setAnnouncementTitleError(String announcementTitleError){this.announcementTitleError = announcementTitleError;}
}
