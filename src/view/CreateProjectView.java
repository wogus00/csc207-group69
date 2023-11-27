package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectController;
import interface_adapter.create_project.CreateProjectState;
import interface_adapter.create_project.CreateProjectViewModel;



import javax.mail.internet.AddressException;
import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class CreateProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "create project";

    private final CreateProjectViewModel createProjectViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField projectNameInputField = new JTextField(15);
    private final JTextField leaderEmailInputField = new JTextField(15);
    private final JTextField memberEmailInputField = new JTextField(15);
    private final CreateProjectController createProjectController;

    private final JButton create;
    private final JButton login;


    public CreateProjectView(CreateProjectController createProjectController,
                             CreateProjectViewModel createProjectViewModel,
                             ViewManagerModel viewManagerModel) {

        this.createProjectController = createProjectController;
        this.createProjectViewModel = createProjectViewModel;
        this.viewManagerModel = viewManagerModel;
        createProjectViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CreateProjectViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel projectNameInfo = new LabelTextPanel(
                new JLabel(CreateProjectViewModel.PROJECTNAME_LABEL), projectNameInputField);
        LabelTextPanel leaderEmailInfo = new LabelTextPanel(
                new JLabel(CreateProjectViewModel.LEADER_EMAIL_LABEL), leaderEmailInputField);
        LabelTextPanel memberEmailInfo = new LabelTextPanel(
                new JLabel(CreateProjectViewModel.MEMBER_EMAIL_LABEL), memberEmailInputField);

        JPanel buttons = new JPanel();
        create = new JButton(CreateProjectViewModel.CREATE_BUTTON_LABEL);
        buttons.add(create);
        login = new JButton(CreateProjectViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(login);

        create.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            CreateProjectState currentState = createProjectViewModel.getState();
                            try {
                                createProjectController.execute(
                                        currentState.getProjectName(),
                                        currentState.getLeaderEmail(),
                                        currentState.getMemberEmail()
                                );
                            } catch (IOException | AddressException e) {
                                throw new RuntimeException(e);
                            }
                            currentState = createProjectViewModel.getState();
                            if (currentState.getProjectNameError() == null) {
                                JOptionPane.showMessageDialog(CreateProjectView.this, "create project successfully");
                            }
                        }
                    }
                }
        );

        login.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            viewManagerModel.setActiveView("log in");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );


        projectNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateProjectState currentState = createProjectViewModel.getState();
                        String text = projectNameInputField.getText() + e.getKeyChar();
                        currentState.setProjectName(text);
                        createProjectViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        leaderEmailInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateProjectState currentState = createProjectViewModel.getState();
                        String text = leaderEmailInputField.getText() + e.getKeyChar();
                        currentState.setLeaderEmail(text);
                        createProjectViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        memberEmailInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateProjectState currentState = createProjectViewModel.getState();
                        String text = memberEmailInputField.getText() + e.getKeyChar();
                        currentState.setMemberEmail(text);
                        createProjectViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(projectNameInfo);
        this.add(leaderEmailInfo);
        this.add(memberEmailInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateProjectState state = (CreateProjectState) evt.getNewValue();
        if (state.getProjectNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getProjectNameError());
        }
    }
}
