package use_case.login;

import entity.Project;

public interface LoginDataAccessInterface {
    boolean projectExist(String identifier);

    Project getProject(String username);
}
