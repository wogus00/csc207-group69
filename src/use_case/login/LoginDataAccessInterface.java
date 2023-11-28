package use_case.login;

import data_access.*;
import entity.Project;

import java.util.ArrayList;

public interface LoginDataAccessInterface {
    boolean existsByName(String identifier);

    Project getProjectInfo(String projectName);

    ArrayList<String> getInfoList(String projectName, InfoListRetrieveStrategy infoListRetrieveStrategy);
}
