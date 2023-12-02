package use_case.create_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateProjectInputDataTest {
    private CreateProjectInputData inputData;
    private String projectName = "testProjectName";
    private String leaderEmail = "leader@gmail.com";
    private ArrayList<String> memberEmails = new ArrayList<>(Arrays.asList("member1@gmail.com", "member2@gmail.com"));


    @BeforeEach
    void setUp() {
        inputData = new CreateProjectInputData(projectName, leaderEmail, memberEmails);
    }

    @Test
    void testGetProjectName() {
        assertEquals(projectName, inputData.getProjectName());
    }
    @Test
    void testGetLeaderEmail() {
        assertEquals(leaderEmail, inputData.getLeaderEmail());
    }

    void testGetMemberEmails() {
        assertEquals(memberEmails, inputData.getMemberEmails());
    }
}
