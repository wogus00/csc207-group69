package use_case.set_leader;

import data_access.FirebaseAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetLeaderInteractorTest {

    @Test
    void successTest() {
        SetLeaderInputData inputData = new SetLeaderInputData("TestProject", "test@example.com");
        FirebaseAccessObject firebaseAccessObject = new FirebaseAccessObject();

        SetLeaderOutputBoundary successPresenter = new SetLeaderOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertEquals(firebaseAccessObject.getProject("TestProject").getLeaderEmail(), "test@example.com");
            }

            @Override
            public void prepareFailView(String error) {
                ;
            }
        };

        SetLeaderInputBoundary interactor = new SetLeaderInteractor(firebaseAccessObject, successPresenter);
        interactor.updateProjectDetails(inputData);
    }
}