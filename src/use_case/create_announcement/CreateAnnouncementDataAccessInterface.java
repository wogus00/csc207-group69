package use_case.create_announcement;


import entity.Announcement;
import entity.CommonAnnouncement;

public interface CreateAnnouncementDataAccessInterface {

    void save(Announcement announcement);
}
