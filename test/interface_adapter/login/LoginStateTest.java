package interface_adapter.login;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginStateTest {

    @Test
    public void testDefaultConstructor() {
        LoginState state = new LoginState();
        assertNull(state.getLoginError());
        assertEquals("", state.getProjectName());
        assertEquals("", state.getUserEmail());
        assertFalse(state.isLogout());
    }

    @Test
    public void testCopyConstructor() {
        LoginState original = new LoginState();
        original.setProjectName("Test Project");
        original.setUserEmail("test@example.com");
        original.setLoginError("Example login error");
        original.setLogout(true);

        LoginState copy = new LoginState(original);
        assertEquals("Test Project", copy.getProjectName());
        assertEquals("test@example.com", copy.getUserEmail());
        assertEquals("Example login error", copy.getLoginError());
        assertTrue(copy.isLogout());
    }


}
