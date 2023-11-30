package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.delete_announcement.DeleteAnnouncementViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import use_case.delete_announcement.DeleteAnnouncementDataAccessInterface;
import view.DeleteAnnouncementView;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DeleteAnnouncementUseCaseFactoryTest {

    @Mock
    private ViewManagerModel viewManagerModel;
    @Mock
    private DeleteAnnouncementViewModel deleteAnnouncementViewModel;
    @Mock
    private DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject;
    @Mock
    private CreateAnnouncementViewModel createAnnouncementViewModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDeleteAnnouncementView() {

        // Act
        DeleteAnnouncementView view = DeleteAnnouncementUseCaseFactory.createDeleteAnnouncementView(viewManagerModel,
                deleteAnnouncementViewModel, deleteAnnouncementDataAccessObject, createAnnouncementViewModel);

        // Assert
        assertNotNull(view);
        // Additional assertions can be made to check if view is configured with correct controller and view model
    }
}
