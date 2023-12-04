package use_case.create_announcement;

import entity.Announcement;

import java.util.ArrayList;

public interface CreateAnnouncementDataAccessInterface {

    void save(String projectName, Announcement announcement);
    ArrayList<String> getMembersEmails(String projectName);

}
