package main.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SwingMainView extends JFrame implements Mediator{
	private CardLayout cardLayout;
	private JPanel mainPanel;
	
	private SwingLoginView loginView;
	private SwingProjectListView projectListView;
	private SwingAccountCreationView accountCreationView;
	private SwingProjectCreationView projectCreationView;
	
	
	public SwingMainView() {
		super("Issue Handle System");
		setVisible(true);
		setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.GREEN);
        
        loginView = new SwingLoginView(this);
        projectListView = new SwingProjectListView(this);
        accountCreationView = new SwingAccountCreationView(this);
        projectCreationView = new SwingProjectCreationView(this);
        
        mainPanel.add(loginView, "LoginView");
        mainPanel.add(projectListView, "ProjectListView");
        mainPanel.add(accountCreationView, "AccountCreationView");
        mainPanel.add(projectCreationView, "ProjectCreationView");
        
        add(mainPanel);
        
        showView("LoginView");
	}

	@Override
	public void notify(SwingView view, String targetViewName) {
		List<String> accessableViewNames = view.getAccessableViewNames();
		if(accessableViewNames.contains(targetViewName)) showView(targetViewName);
		else new Exception();
	}
	
	private void showView(String viewName) {
		SwingView view = loginView;
		if(viewName == "LoginView") view = loginView;
		else if(viewName == "ProjectListView") view = projectListView;
		else if(viewName == "AccountCreationView") view = accountCreationView;
		else if(viewName == "ProjectCreationView") view = projectCreationView;
		
		cardLayout.show(mainPanel, viewName);
		this.setSize(view.getPreferredSize());
		view.refresh();
	}
	
	public SwingLoginView getLoginView() {
		return loginView;
	}
	
	public SwingProjectListView getProjectListView() {
		return projectListView;
	}
	
	public SwingAccountCreationView getAccountCreationView() {
		return accountCreationView;
	}
	
	public SwingProjectCreationView getProjectCreationView() {
		return projectCreationView;
	}
}
