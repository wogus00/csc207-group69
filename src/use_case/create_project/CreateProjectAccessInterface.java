package use_case.create_project;

import entity.CommonProject;

public interface CreateProjectAccessInterface {
    boolean existsByName(String identifier);

    void save(CommonProject commonProject);

    User get(String username);
}
