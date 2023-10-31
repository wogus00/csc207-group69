package use_case.create_project;

import entity.Project;

public interface CreateProjectDataAccessInterface {
    boolean existsByName(String projectName);

    void save(Project project);
}
