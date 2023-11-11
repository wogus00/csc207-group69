package use_case.create_announcement;


import entity.CommonAnnouncement;

public interface CreateAnnouncementDataAccessInterface {

    void addAnnouncement(CommonAnnouncement announcement);
}
