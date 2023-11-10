package use_case.update_project;

import java.util.ArrayList;
import java.util.List;

public class UpdateProjectInputData {
    private String leaderEmail;
    private List<String> memberEmailsToAdd = new ArrayList<>();
    private List<String> memberEmailsToRemove = new ArrayList<>();

    public UpdateProjectInputData(String leaderEmail, List<String> memberEmailsToAdd, List<String> memberEmailsToRemove) {
        this.leaderEmail = leaderEmail;
        this.memberEmailsToAdd = memberEmailsToAdd;
        this.memberEmailsToRemove = memberEmailsToRemove;
    }

    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }

    public void addMemberEmail(String email) {
        if (email != null && !email.isEmpty() && !memberEmailsToAdd.contains(email)) {
            memberEmailsToAdd.add(email);
        }
    }

    public void removeMemberEmail(String email) {
        if (email != null && !email.isEmpty() && !memberEmailsToRemove.contains(email)) {
            memberEmailsToRemove.add(email);
        }
    }
}
