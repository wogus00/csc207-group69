package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.remove_email.RemoveEmailViewModel;
import interface_adapter.set_leader.SetLeaderViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.remove_email.RemoveEmailDataAccessInterface;
import use_case.set_leader.SetLeaderDataAccessInterface;
import view.RemoveEmailView;
import view.SetLeaderView;

import static org.junit.Assert.assertNotNull;

public class SetLeaderUseCaseFactoryTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private SetLeaderViewModel mockSetLeaderViewModel;
    @Mock
    private SetLeaderDataAccessInterface mockUserDataAccessObject;
    @Mock
    private MainPageViewModel mockMainPageViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveEmailView() {
        SetLeaderView view = SetLeaderUseCaseFactory.setLeaderView(mockViewManagerModel, mockSetLeaderViewModel, mockUserDataAccessObject, mockMainPageViewModel);

        assertNotNull(view);
    }



}
