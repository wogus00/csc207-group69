package use_case.remove_email;

import data_access.FirebaseAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveEmailInteractorTest {

    @Test
    void successTest() {
        RemoveEmailInputData inputData = new RemoveEmailInputData("TestProject", "test@example.com");
        FirebaseAccessObject firebaseAccessObject = new FirebaseAccessObject();

        RemoveEmailOutputBoundary successPresenter = new RemoveEmailOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertFalse(firebaseAccessObject.getProject("TestProject").getMemberEmails().contains("test@example.com"));
            }

            @Override
            public void prepareFailView(String error) {
                ;
            }
        };

        RemoveEmailInputBoundary interactor = new RemoveEmailInteractor(firebaseAccessObject, successPresenter);
        interactor.updateProjectDetails(inputData);
    }
}