package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProjectFactory {
    Project create(String name, User leader, String detail, List<User> members, String timeSlot);
}
