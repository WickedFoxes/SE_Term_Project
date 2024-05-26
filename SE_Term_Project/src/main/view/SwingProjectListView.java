package main.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import main.domain.Project;

public class SwingProjectListView extends SwingView implements LogoutableView {
	JButton logoutButton, createAccountButton, createProjectButton;
	JPanel projectPanel;
	JScrollPane scrollPane;
	GridBagLayout grid;
	GridBagConstraints contstraints;
	List<JButton> projectButtons;
	
	public SwingProjectListView(Mediator mediator) {
		super(mediator, new Dimension(700, 600));
		
		logoutButton = new JButton("Logout");
		createAccountButton = new JButton("<HTML><body><center>Create<br>Account</center></body></HTML>");
		createProjectButton = new JButton("<HTML><body><center>Create<br>Project</center></body></HTML>");
		
		grid = new GridBagLayout();
		contstraints = new GridBagConstraints();
		contstraints.fill = GridBagConstraints.HORIZONTAL;
		contstraints.anchor = GridBagConstraints.PAGE_START;
		contstraints.insets = new Insets(5, 5, 5, 5);
		projectPanel = new JPanel();
		projectPanel.setLayout(grid);
		projectButtons = new ArrayList<JButton>();
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(projectPanel);
		
		logoutButton.setBounds(550, 30, 100, 40);
		createAccountButton.setBounds(430, 30, 100, 40);
		createProjectButton.setBounds(310, 30, 100, 40);
		scrollPane.setBounds(80, 90, 540, 430);
		
		add(logoutButton);
		add(createAccountButton);
		add(createProjectButton);
		add(scrollPane);
	}
	
	public void setProjectButtons(List<Project> projects, List<ActionListener> listeners) {
		//System.out.println("set project buttons, num: " +projects.size() + " ("+this.getClass().toString()+")");
		int projectSize = projects.size();
		String name;
		JButton button;
		
		for(JButton btn : projectButtons) projectPanel.remove(btn);
		projectButtons.clear();
		
		for(int i = 0; i < projectSize; i++) {
			name = projects.get(i).getName();
			button = new JButton(name);
			button.addActionListener(listeners.get(i));
			button.setPreferredSize(new Dimension(500, 40));
			addGrid(button, 0, i, 1, 1, 1, 0);
			projectButtons.add(button);
		}
	}
	
	private void addGrid(Component c, int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty) {
		contstraints.gridx = gridx;
		contstraints.gridy = gridy;
		contstraints.gridwidth = gridwidth;
		contstraints.gridheight = gridheight;
		contstraints.weightx = weightx;
		contstraints.weighty = weighty;
		grid.setConstraints(c, contstraints);
		projectPanel.add(c);
	}
	
	public void setCreateAccountButtonVisible(boolean isVisible) {
		createAccountButton.setVisible(isVisible);
	}
	
	public void setCreateProjectButtonVisible(boolean isVisible) {
		createProjectButton.setVisible(isVisible);
	}
	
	public void setCreateAccountListener(ActionListener listener) {
		createAccountButton.addActionListener(listener);
	}
	
	public void setCreateProjectListener(ActionListener listener) {
		createProjectButton.addActionListener(listener);
	}
	
	@Override
	public void setLogoutListener(ActionListener listener) {
		logoutButton.addActionListener(listener);
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

	@Override
	public void refresh() { }
}
