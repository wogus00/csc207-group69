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
    // test not removing similar emails Abc@gmail.com, abc@gmail.com
    // test rise an error if the email to remove doesn't exists
    // test rise an error if we are trying to remove from empty array list
}