package use_case.create_task;

import entity.Task;

import java.util.ArrayList;

public interface CreateTaskDataAccessInterface {
    void saveTask(String projectName, Task newTask);

    boolean taskNameExists(String projectName, String taskName);

    boolean memberExists(String projectName, ArrayList<String> workingMembersList);
}
