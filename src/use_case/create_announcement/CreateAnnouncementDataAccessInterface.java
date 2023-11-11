package use_case.create_announcement;


import entity.Announcement;

public interface CreateAnnouncementDataAccessInterface {
    boolean existsByName(String announcementTitle);

    void save(Announcement announcement);
}
