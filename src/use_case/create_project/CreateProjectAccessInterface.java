package use_case.create_project;

import entity.Project;
import entity.User;

public interface CreateProjectAccessInterface {
    boolean existsByName(String identifier);

    void save(Project project);

    User get(String username);
}
