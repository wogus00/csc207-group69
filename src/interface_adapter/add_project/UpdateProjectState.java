package interface_adapter.update_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateProjectState {
    private String leaderEmail;
    private List<String> memberEmails;

    public UpdateProjectState(UpdateProjectState copy) {
        this.leaderEmail = copy.leaderEmail;
        this.memberEmails = new ArrayList<>(copy.memberEmails);
    }

    public UpdateProjectState() {
        this.leaderEmail = "";
        this.memberEmails = new ArrayList<>();
    }

    public void setLeaderEmail(String newLeaderEmail) {
        // Include validation if needed
        this.leaderEmail = newLeaderEmail;
    }

    public void addMemberEmails(String... emails) {
        // Include validation and duplication checks if needed
        memberEmails.addAll(Arrays.asList(emails));
    }

    public void removeMemberEmails(String... emails) {
        memberEmails.removeAll(Arrays.asList(emails));
    }

    // Optional getter methods
}
