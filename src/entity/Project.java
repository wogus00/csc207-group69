package entity;

import java.util.ArrayList;

public interface Project {
    String getProjectName();
    String getLeaderEmail();

    void setLeaderEmail(String email);

    ArrayList<String> getMemberEmails();
    void addMemberEmails(String email);
    void removeMemberEmails(String email);
}
