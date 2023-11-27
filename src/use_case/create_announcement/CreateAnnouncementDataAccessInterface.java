package use_case.create_announcement;

import entity.Announcement;
import entity.Project;

public interface CreateAnnouncementDataAccessInterface {

    void save(Announcement announcement);

    String getProjectNameFromAnnouncementId(String announcementId);

    Project getProjectInfo(String projectName);
}
