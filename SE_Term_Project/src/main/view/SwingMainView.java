package main.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SwingMainView extends JFrame implements Mediator{
	private CardLayout cardLayout;
	private JPanel mainPanel;
	
	private SwingLoginView loginView;
	private SwingProjectListView projectListView;
	
	
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
        
        mainPanel.add(loginView, "LoginView");
        mainPanel.add(projectListView, "ProjectListView");
        
        add(mainPanel);
        
        showView("LoginView");
	}
	
	public SwingLoginView getLoginView() {
		return loginView;
	}
	
	public SwingProjectListView getProjectListView() {
		return projectListView;
	}

	@Override
	public void notify(SwingView view, String targetViewName) {
		if(view instanceof SwingLoginView)
			if(targetViewName != "ProjectListView") new Exception();
		else if(view instanceof SwingProjectListView)
			if(targetViewName != "LoginView" 
			|| targetViewName != "IssueListView"
			|| targetViewName != "ProjectCreationView"
			|| targetViewName != "AccountCreationView") new Exception();
		
		showView(targetViewName);
	}
	
	private void showView(String viewName) {
		cardLayout.show(mainPanel, viewName);
		if(viewName == "LoginView") this.setSize(loginView.getPreferredSize());
		else if(viewName == "ProjectListView") this.setSize(projectListView.getPreferredSize());
	}
}
