package entity;

import java.util.List;

public class CommonProjectFactory implements ProjectFactory {
    @Override
    public Project create(String name, User leader, String detail, List<User> members, String timeSlot) {
        return new CommonProject(name, leader, detail, members, timeSlot);
    }
}
