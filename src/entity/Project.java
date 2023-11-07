package entity;

import java.util.List;

public interface Project {
    String getName();

    void setName(String name);

    User getLeader();

    void setLeader(User leader);

    String getDetail();

    void setDetail(String detail);

    List<User> getMembers();

    void addMember(User member);

    void removeMember(User member);

    String getTimeSlot();

    void setTimeSlot(String timeSlot);

    void checkUserAssignment(List<CommonProject> commonProjects) throws Exception;
}
