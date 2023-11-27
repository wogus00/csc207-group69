package data_access;

import java.util.List;

public interface InfoListGetter {

    public List<String> getInfoList(String projectName, FirebaseAccessObject firebaseAccessObject);
}
