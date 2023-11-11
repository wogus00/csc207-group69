package use_case.login;

import entity.Project;

public interface LoginDataAccessInterface {
    boolean existsByName(String identifier);

    Project getProject(String username);
}
