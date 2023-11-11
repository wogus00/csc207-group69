package use_case.add_email;

import entity.Project;

public interface AddEmailDataAccessInterface {
    void addMemberToProject(String projectName, String email);
}