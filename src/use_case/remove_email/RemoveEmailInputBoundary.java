package use_case.remove_email;

import use_case.add_email.AddEmailInputData;

public interface RemoveEmailInputBoundary {
    void updateProjectDetails(RemoveEmailInputData removeEmailInputData);
}
