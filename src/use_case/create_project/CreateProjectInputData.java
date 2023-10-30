package use_case.create_project;

import java.util.ArrayList;

public class CreateProjectInputData {

    final private String project;

    public CreateProjectInputData(String projectName, String leaderEmail, ArrayList<String> memberEmail) {
        this.project = projectName;
    }

    String getProject() {
        return project;
    }

    public String getProject_name() {
        return this.project;
    }
}
