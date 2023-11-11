package use_case.delete_announcement;

import entity.Announcement;

import java.util.ArrayList;
import java.util.Map;

public interface DeleteAnnouncementDataAccessInterface {
    boolean searchValidUser(String identifier);

    Announcement clear(String announcementTitle);
}
