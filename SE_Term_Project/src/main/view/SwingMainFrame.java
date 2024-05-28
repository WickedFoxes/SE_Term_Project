package main.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SwingMainFrame extends JFrame implements Mediator{
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private String currentViewName;
	
	private SwingLoginView loginView;
	private SwingProjectListView projectListView;
	private SwingAccountCreationView accountCreationView;
	private SwingProjectCreationView projectCreationView;
	private SwingIssueListView issueListView;
	private SwingIssueCreationView issueCreationView;
	private SwingIssueDetailView issueDetailView;
	private IssueFilterFrame issueFilterFrame;
	
	public SwingMainFrame() {
		super("Issue Handle System");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        int x = (int) ((screenSize.getWidth() - frameSize.getWidth()) / 3);
        int y = (int) ((screenSize.getHeight() - frameSize.getHeight()) / 4);
        setLocation(x, y);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        loginView = new SwingLoginView(this);
        projectListView = new SwingProjectListView(this);
        accountCreationView = new SwingAccountCreationView(this);
        projectCreationView = new SwingProjectCreationView(this);
    	issueListView = new SwingIssueListView(this);
    	issueCreationView = new SwingIssueCreationView(this);
    	issueDetailView = new SwingIssueDetailView(this);
    	issueFilterFrame = new IssueFilterFrame(this, new SwingIssueFilterView(this));
        
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
	public void changeView(SwingView view, String targetViewName) {
		List<String> accessableViewNames = view.getAccessableViewNames();
		if(accessableViewNames.contains(targetViewName)) showView(targetViewName);
		else System.out.println("Error: cannot access View ("+this.getClass().toString()+")");
	}
	
	@Override
	public String getCurrentViewName() {
		return currentViewName;
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
			issueFilterFrame.setVisible(true);
			return;
		}
		else System.out.println("Error: invalid viewName ("+this.getClass().toString()+")");
		
		cardLayout.show(mainPanel, viewName);
		this.setSize(view.getPreferredSize());
		view.refresh();
		currentViewName = viewName;
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
		return issueFilterFrame.getIssueFilterView();
	}
	
	private class IssueFilterFrame extends JFrame {
		private SwingIssueFilterView viewPanel;
		public IssueFilterFrame(SwingMainFrame mainFrame, SwingIssueFilterView view) {
			this.viewPanel = view;
			setLocationRelativeTo(mainFrame);
	        setResizable(false);
	        setSize(viewPanel.getPreferredSize());
			add(viewPanel);
		}
		
		public SwingIssueFilterView getIssueFilterView() {
			return viewPanel;
		}
	}
}
