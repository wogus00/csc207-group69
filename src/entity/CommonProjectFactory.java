package entity;

import java.util.ArrayList;

public class CommonProjectFactory implements ProjectFactory {
    @Override
    public Project create(String projectName, String leaderEmail, ArrayList<String> memberEmails) {
         return new CommonProject(projectName, leaderEmail, memberEmails);
    }
}
