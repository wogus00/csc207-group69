package entity;

import java.util.ArrayList;

public interface Project {
    String getProjectName();
    String getLeaderEmail();
    ArrayList<String> getMemberEmails();
}
