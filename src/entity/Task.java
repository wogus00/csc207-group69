package entity;

public class Task extends Event {

    private User supervisor;
    private Boolean completionStatus;

    public Task(String name, String responsibility, String detail, String start_end_time, User supervisor) {
        super(name, responsibility, detail, start_end_time);
        this.supervisor = supervisor;
        this.completionStatus = false;
    }

    // Getter and setter for supervisor
    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    // Getter and setter for completionStatus
    public Boolean getCompletionStatus() {
        return completionStatus;
    }

    public void changeCompletionStatus(Boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    // ... rest of the class body
}
