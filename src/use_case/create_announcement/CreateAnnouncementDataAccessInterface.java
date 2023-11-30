package use_case.create_announcement;


import entity.Announcement;

public interface CreateAnnouncementDataAccessInterface {

    void save(String projectName, Announcement announcement);
}
