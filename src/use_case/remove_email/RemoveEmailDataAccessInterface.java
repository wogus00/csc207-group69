package use_case.remove_email;

public interface RemoveEmailDataAccessInterface {
    void removeMemberFromProject(String projectName, String email);
}
