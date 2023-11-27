package use_case.delete_announcement;

import entity.Announcement;
import entity.CommonAnnouncement;

import java.util.ArrayList;
import java.util.Map;

public interface DeleteAnnouncementDataAccessInterface {

    CommonAnnouncement getAnnouncementById(String announcementId);

    boolean deleteAnnouncement(String announcementId);
}
