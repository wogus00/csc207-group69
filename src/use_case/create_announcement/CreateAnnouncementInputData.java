package use_case.create_announcement;

import java.util.UUID;

public class CreateAnnouncementInputData {

    final private String announcementTitle;

    final private String message;

    final private String author;

    final private String announcementId;

    public CreateAnnouncementInputData(String announcementTitle, String message, String author) {
        this.announcementTitle = announcementTitle;
        this.message = message;
        this.author = author;
        this.announcementId = UUID.randomUUID().toString();
    }

    String getAnnouncementTitle(){return announcementTitle;}

    String getMessage(){return message;}

    String getAuthor(){return this.announcementId;}

    String getAnnouncementId(){return announcementId;}
}
