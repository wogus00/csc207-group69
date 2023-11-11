package use_case.create_announcement;

public class CreateAnnouncementInputData {

    final private String announcementTitle;

    final private String message;

    public CreateAnnouncementInputData(String announcementTitle, String message) {
        this.announcementTitle = announcementTitle;
        this.message = message;
    }

    String getAnnouncementTitle(){return announcementTitle;}

    String getMessage(){return message;}


}
