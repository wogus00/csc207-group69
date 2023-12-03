package data_access;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public interface ProjectInfoAccessor {
    public ArrayList<String> getAnnouncementInfoList();
    public ArrayList<String> getMeetingInfoList();
    public ArrayList<String> getTaskInfoList();

}
