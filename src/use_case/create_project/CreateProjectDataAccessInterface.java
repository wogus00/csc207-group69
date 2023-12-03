package use_case.create_project;

import entity.Meeting;
import entity.Project;

import java.util.concurrent.ExecutionException;

public interface CreateProjectDataAccessInterface {
    boolean existsByName(String projectName);

    void save(Project project);

}
