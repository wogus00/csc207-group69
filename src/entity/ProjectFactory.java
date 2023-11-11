package entity;

import java.util.ArrayList;

public interface ProjectFactory {
    Project create(String projectName, String leaderEmail, ArrayList<String> memberEmails);
}
