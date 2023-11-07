package entity;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

class CommonProject implements Project {

    private String name;
    private User leader;
    private String detail;
    private final List<User> members;
    private String timeSlot;

    CommonProject(String name, User leader, String detail, List<User> members, String timeSlot) {
        this.name = name;
        this.leader = leader;
        this.detail = detail;
        this.members = members;
        this.timeSlot = timeSlot;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public User getLeader() {
        return leader;
    }

    @Override
    public void setLeader(User leader){
        this.leader = leader;
    }

    @Override
    public String getDetail() {
        return detail;
    }

    @Override
    public void setDetail(String detail){
        this.detail = detail;
    }

    @Override
    public List<User> getMembers() {
        return members;
    }

    @Override
    public void addMember(User member) {
        members.add(member);
    }

    @Override
    public void removeMember(User member) {
        members.remove(member);
    }

    @Override
    public String getTimeSlot() {
        return timeSlot;
    }

    @Override
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public void checkUserAssignment(List<CommonProject> commonProjects) throws Exception {
        Map<User, List<String>> userTimeSlots = new HashMap<>();

        for (CommonProject commonProject : commonProjects) {
            for (User user : commonProject.members) {
                if (userTimeSlots.containsKey(user)) {
                    if (userTimeSlots.get(user).contains(commonProject.timeSlot)) {
                        throw new Exception("User " + user.getUserame() + " is already assigned to another project during time slot " + commonProject.timeSlot);
                    } else {
                        userTimeSlots.get(user).add(commonProject.timeSlot);
                    }
                } else {
                    List<String> timeSlots = new ArrayList<>();
                    timeSlots.add(commonProject.timeSlot);
                    userTimeSlots.put(user, timeSlots);
                }
            }
        }
    }
}