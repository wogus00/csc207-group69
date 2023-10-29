package use_case.create_project;

import entity.Project;

public interface CreateProjectAccessInterface {
    boolean existsByName(String identifier);

    void save(Project project);

    User get(String username);
}
