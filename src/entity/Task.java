package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Task {

    String getProjectName();
    String getTaskName();
    String getSupervisor();
    LocalDate getDeadline();
    ArrayList<String> getWorkingMembersList();
    String getComments();
    boolean getStatus();
}
