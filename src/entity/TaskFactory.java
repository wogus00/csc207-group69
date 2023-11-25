package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public interface TaskFactory {
    Task create(String projectName, String taskName, String supervisor, ArrayList<String> workingMembersList, LocalDate deadline, String comments, boolean status);
}
