package use_case.remove_email;

public interface RemoveEmailDataAccessInterface {
    void removeMemberToProject(String projectName, String email);
}
