package use_case.create_announcement;

public class CreateAnnouncementInputData {

    final private String announcementTitle;

    final private String message;

    final private String author;

    public CreateAnnouncementInputData(String announcementTitle, String message, String author) {
        this.announcementTitle = announcementTitle;
        this.message = message;
        this.author = author;
    }

    String getAnnouncementTitle(){return announcementTitle;}

    String getMessage(){return message;}

    String getAuthor(){return author;}
}
