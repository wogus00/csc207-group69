package interface_adapter.modify_meeting;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.modify_meeting.ModifyMeetingOutputData;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ModifyMeetingPresenterTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private ModifyMeetingViewModel mockModifyMeetingViewModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;

    private ModifyMeetingPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ModifyMeetingPresenter(mockViewManagerModel, mockModifyMeetingViewModel, mockMainPageViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        ModifyMeetingOutputData response = new ModifyMeetingOutputData("Name", emails, "11/11/1111", "11:11", "22:22", "Project");

        // Act
        presenter.prepareSuccessView(response);

        // Assert
        verify(mockMainPageViewModel).setState(any(MainPageState.class));
        verify(mockViewManagerModel).setActiveView(mockModifyMeetingViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }
}
