package use_case.create_project;

import com.beust.ah.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CreateProjectOutputDataTest {

    private CreateProjectOutputData outputData;

    private String projectName;
    private String leaderEmail;
    private ArrayList<String> memberEmails;
    private boolean useCaseFailed;

    @BeforeEach
    void setUpFailed() {
        String projectName = "projectName";
        String leaderEmail = "leader@gmail.com";
        ArrayList<String> memberEmails = new ArrayList<>(Arrays.asList("member1@gmail.com", "member2@gmail.com"));
        outputData = new CreateProjectOutputData(projectName, leaderEmail, memberEmails, true);
    }
    @Test
    void testGetProjectName() {
        assertEquals("projectName", outputData.getProjectName());

    }

    @Test
    void testGetLeaderEmail() {
        assertEquals("leader@gmail.com", outputData.getLeaderEmail());


    }

    @Test
    void testGetMemberEmails() {
        ArrayList<String> email = new ArrayList<>();
        email.add("member1@gmail.com");
        email.add("member2@gmail.com");
        assertEquals(email, outputData.getMemberEmails());
    }
}
