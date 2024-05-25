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
        
        mainPanel.add(loginView, "LoginView");
        mainPanel.add(projectListView, "ProjectListView");
        mainPanel.add(accountCreationView, "AccountCreationtView");
        
        add(mainPanel);
        
        showView("LoginView");
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
		
		this.setSize(view.getPreferredSize());
		cardLayout.show(mainPanel, viewName);
	}
}
