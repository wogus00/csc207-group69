package use_case.login;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginInputDataTest {

    @Test
    public void testInputData(){
        String projectName = "Test Project";
        String userEmail = "test@example.com";
        LoginInputData inputData = new LoginInputData(projectName, userEmail);
        assertEquals(projectName, inputData.getProjectName());
        assertEquals(userEmail, inputData.getUserEmail());
    }
}
