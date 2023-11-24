package use_case.create_project;

import entity.Meeting;
import entity.Project;

public interface CreateProjectDataAccessInterface {
    boolean existsByName(String projectName);

    void save(Project project);

    void saveMeeting(Meeting meeting);
}
