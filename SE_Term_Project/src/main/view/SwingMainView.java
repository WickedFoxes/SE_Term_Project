package main.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
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
	private SwingIssueListView issueListView;
	private SwingIssueCreationView issueCreationView;
	private SwingIssueDetailView issueDetailView;
	private SwingIssueFilterView issueFilterView;
	private SwingIssueFilterPopup issueFilterPopup;
	
	public SwingMainView() {
		super("Issue Handle System");
		setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        loginView = new SwingLoginView(this);
        projectListView = new SwingProjectListView(this);
        accountCreationView = new SwingAccountCreationView(this);
        projectCreationView = new SwingProjectCreationView(this);
    	issueListView = new SwingIssueListView(this);
    	issueCreationView = new SwingIssueCreationView(this);
    	issueDetailView = new SwingIssueDetailView(this);
    	issueFilterView = new SwingIssueFilterView(this);
    	issueFilterPopup = new SwingIssueFilterPopup(this, issueFilterView);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(loginView, "LoginView");
        mainPanel.add(projectListView, "ProjectListView");
        mainPanel.add(accountCreationView, "AccountCreationView");
        mainPanel.add(projectCreationView, "ProjectCreationView");
        mainPanel.add(issueListView, "IssueListView");
        mainPanel.add(issueCreationView, "IssueCreationView");
        mainPanel.add(issueDetailView, "IssueDetailView");
        
        this.add(mainPanel);
        showView("LoginView");
	}

	@Override
	public void notify(SwingView view, String targetViewName) {
		List<String> accessableViewNames = view.getAccessableViewNames();
		if(accessableViewNames.contains(targetViewName)) showView(targetViewName);
		else System.out.println("Error: cannot access View ("+this.getClass().toString()+")");
	}
	
	private void showView(String viewName) {
		SwingView view = loginView;
		
		if(viewName == "LoginView") view = loginView;
		else if(viewName == "ProjectListView") view = projectListView;
		else if(viewName == "AccountCreationView") view = accountCreationView;
		else if(viewName == "ProjectCreationView") view = projectCreationView;
		else if(viewName == "IssueListView") view = issueListView;
		else if(viewName == "IssueCreationView") view = issueCreationView;
		else if(viewName == "IssueDetailView") view = issueDetailView;
		else if(viewName == "IssueFilterView") {
			issueFilterPopup.showPopup();
			return;
		}
		else System.out.println("Error: invalid viewName ("+this.getClass().toString()+")");
		
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
	
	public SwingIssueListView getIssueListView() {
		return issueListView;
	}
	
	public SwingIssueCreationView getIssueCreationView() {
		return issueCreationView;
	}
	
	public SwingIssueDetailView getIssueDetailView() {
		return issueDetailView;
	}
	
	public SwingIssueFilterView getIssueFilterView() {
		return issueFilterView;
	}
}
