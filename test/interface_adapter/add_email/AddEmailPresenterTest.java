package interface_adapter.add_email;

import app.Main;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_email.AddEmailPresenter;
import interface_adapter.add_email.AddEmailViewModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AddEmailPresenterTest {

    @Test
    public void testPrepareSuccessView() {
        AddEmailViewModel viewModel = new AddEmailViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MainPageViewModel mainPageViewModel = new MainPageViewModel();
        AddEmailPresenter presenter = new AddEmailPresenter(viewModel, viewManagerModel, mainPageViewModel);

        MainPageState mainPageState = new MainPageState();
        mainPageState.setMemberEmail(new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com")));
        mainPageViewModel.setState(mainPageState);

        presenter.prepareSuccessView("newmember@example.com");

        ArrayList<String> updatedMemberList = mainPageState.getMemberEmail();
        assertTrue(updatedMemberList.contains("newmember@example.com"));
        assertEquals(3, updatedMemberList.size());
    }

    @Test
    public void testPrepareFailView() {
        AddEmailViewModel viewModel = new AddEmailViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MainPageViewModel mainPageViewModel = new MainPageViewModel();
        AddEmailPresenter presenter = new AddEmailPresenter(viewModel, viewManagerModel, mainPageViewModel);

        presenter.prepareFailView("Invalid email");

    }

}
