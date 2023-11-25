package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class CommonTaskFactory implements TaskFactory {

    @Override
    public Task create(String projectName, String taskName, String supervisor, ArrayList<String> workingMembersList, LocalDate deadline, String comments, boolean status) {
        return new CommonTask(projectName, taskName, supervisor, workingMembersList, deadline, comments, false);
    }
}
