package data_access;

import java.util.List;

public interface InfoListRetrieveStrategy {

    public List<String> getInfoList(String projectName, FirebaseAccessObject firebaseAccessObject);
}
