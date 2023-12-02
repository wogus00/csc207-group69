package entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommonProjectFactoryTest {

    @Test
    public void testCreateCommonProject() {
        CommonProjectFactory factory = new CommonProjectFactory();
        String projectName = "projectName";
        String leaderEmail = "leaderEmail@gmail.com";
        ArrayList<String> memberEmails = new ArrayList<>();
        memberEmails.add("member1@gmail.com");
        memberEmails.add("member2@gmail.com");

        CommonProject project = (CommonProject) factory.create(projectName, leaderEmail, memberEmails);

        ArrayList<String> emails = new ArrayList<>(Arrays.asList("member1@gmail.com", "member2@gmail.com"));
        assertNotNull(project);
        assertEquals("projectName", project.getProjectName());
        assertEquals("leaderEmail@gmail.com", project.getLeaderEmail());
        assertEquals(emails, project.getMemberEmails());
    }
}
