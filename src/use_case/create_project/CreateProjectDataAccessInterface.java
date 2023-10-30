package use_case.create_project;

public interface CreateProjectDataAccessInterface {
    boolean existsByName(String projectName);
}
