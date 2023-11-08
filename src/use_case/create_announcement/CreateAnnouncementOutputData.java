package use_case.create_announcement;

public class CreateAnnouncementOutputData {

    private final String announcementTitle;

    private final String message;

    private String creationTime;

    private boolean useCaseFailed;

    public CreateAnnouncementOutputData(String announcementTitle, String message, String creationTime, boolean useCaseFailed) {
        this.announcementTitle = announcementTitle;
        this.message = message;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getAnnouncementTitle(){return announcementTitle;}

    public String getMessage(){return message;}

    public String getCreationTime(){return creationTime;}

    public void setCreationTime(String creationTime){this.creationTime = creationTime;}

}
