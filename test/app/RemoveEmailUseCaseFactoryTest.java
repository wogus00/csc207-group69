package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_email.AddEmailViewModel;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.remove_email.RemoveEmailViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.add_email.AddEmailDataAccessInterface;
import use_case.remove_email.RemoveEmailDataAccessInterface;
import view.RemoveEmailView;

import static org.junit.Assert.assertNotNull;

public class RemoveEmailUseCaseFactoryTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private RemoveEmailViewModel mockRemoveEmailViewModel;
    @Mock
    private RemoveEmailDataAccessInterface mockUserDataAccessObject;
    @Mock
    private MainPageViewModel mockMainPageViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveEmailView() {
        RemoveEmailView view = RemoveEmailUseCaseFactory.removeEmailView(mockViewManagerModel, mockRemoveEmailViewModel, mockUserDataAccessObject, mockMainPageViewModel);

        assertNotNull(view);
    }
}
