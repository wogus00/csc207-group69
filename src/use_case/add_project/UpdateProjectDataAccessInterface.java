package use_case.update_project;

import entity.Project;

public interface UpdateProjectDataAccessInterface {
    boolean existsByName(String memberEmails);

    void save(Project project);
}