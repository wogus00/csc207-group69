package entity;

import java.util.List;
import java.util.ArrayList;

public class Project {

    private String name;
    private String leader;
    private String detail;
    private List<String> members = new ArrayList<>();

    public Project(String name, String leader, String detail, List<String> members) {
        this.name = name;
        this.leader = leader;
        this.detail = detail;
        this.members = members;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for leader
    public String getLeader() {
        return leader;
    }

    // Getter for detail
    public String getDetail() {
        return detail;
    }

    // Getter for members
    public List<String> members() {
        return members;
    }

    // Setter for members (Optional)
    public void setMembers(List<String> members) {
        this.members = members;
    }
}
