package entity;

public interface ProjectFactory {
    Project create(String projectName, String leaderEmail, String memberEmails);
}
