package entity;

import java.time.LocalDateTime;

public class CommonAnnouncement implements Announcement {
    private final String announcementTitle;

    private final String message;

    private final LocalDateTime creationTime;

    private final String author;

    private final String id;

    public CommonAnnouncement(String announcementTitle, String message, LocalDateTime creationTime, String author, String id) {
        this.announcementTitle = announcementTitle;
        this.message = message;
        this.creationTime = creationTime;
        this.author = author;
        this.id = id;
    }

    @Override
    public String getAnnouncementTitle(){return announcementTitle;}

    @Override
    public String getMessage(){return message;}

    @Override
    public LocalDateTime getCreationTime(){return creationTime;}

    @Override
    public String getAuthor(){return author;}

    @Override
    public String getId(){return id;}
}
