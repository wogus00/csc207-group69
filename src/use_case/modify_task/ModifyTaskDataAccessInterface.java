package use_case.modify_task;

import entity.Task;

import java.util.ArrayList;

public interface ModifyTaskDataAccessInterface {
    boolean taskNameExists(String projectName, String taskName);

    void deleteOldTask(String projectName, String oldTaskName);

    void saveTask(String projectName, Task newTask);

    boolean memberExists(String projectName, ArrayList<String> workingMembersList);
}
