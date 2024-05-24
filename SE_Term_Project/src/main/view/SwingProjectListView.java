package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.domain.Project;

public class SwingProjectListView extends SwingView {
	JButton logoutButton, createAccountButton, createProjectButton;
	JPanel projectPanel;
	List<JButton> projectButtons;
	
	public SwingProjectListView(Mediator mediator) {
		super(mediator, new Dimension(700, 600));
		
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(550, 30, 100, 40);
		createAccountButton = new JButton("<HTML><body><center>Create<br>Account</center></body></HTML>");
		createAccountButton.setBounds(430, 30, 100, 40);
		createProjectButton = new JButton("<HTML><body><center>Create<br>Project</center></body></HTML>");
		createProjectButton.setBounds(310, 30, 100, 40);

		projectPanel = new JPanel();
		projectPanel.setBounds(80, 90, 540, 430);
		projectPanel.setBackground(Color.GREEN);
		projectPanel.setLayout(new FlowLayout());
		projectButtons = new ArrayList<JButton>();
		
		add(logoutButton);
		add(createAccountButton);
		add(createProjectButton);
		add(projectPanel);
		
		setVisible(true);
	}
	
	public void setProjectButtons(List<Project> projects, List<ActionListener> listeners) {
		int projectSize = projects.size();
		String name;
		JButton button;
		projectButtons.clear();
		
		for(int i = 0; i < projectSize; i++) {
			name = projects.get(i).getName();
			button = new JButton(name);
			button.addActionListener(listeners.get(i));
			projectButtons.add(button);
		}
	}
	
	public void setCreateAccountButtonVisible(boolean isVisible) {
		createAccountButton.setVisible(isVisible);
	}
	public void setCreateProjectButtonVisible(boolean isVisible) {
		createProjectButton.setVisible(isVisible);
	}
	public void setLogoutListener(ActionListener listener) {
		logoutButton.addActionListener(listener);
	}
	public void setCreateAccountListener(ActionListener listener) {
		createAccountButton.addActionListener(listener);
	}
	public void setCreateProjectListener(ActionListener listener) {
		createProjectButton.addActionListener(listener);
	}
	
	@Override
	public List<String> getAccessableViewNames() {
		List<String> list = new ArrayList<String>();
		list.add("LoginView");
		list.add("IssueListView");
		list.add("ProjectCreationView");
		list.add("AccountCreationView");
		return list;
	}
}
