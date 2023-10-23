package use_case.create_project;

public class CreateProjectInputData {

    final private String project;

    public CreateProjectInputData(String project) {
        this.project = project;
    }

    String getProject() {
        return project;
    }
}
