package main.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import main.domain.Issue;
import main.domain.User;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;

public class SwingIssueListView extends SwingView implements ReturnableView, LogoutableView {
	private JButton logoutButton, createIssueButton, filterButton, returnButton;
	private JLabel infoLabel;
	private JScrollPane scrollPane;
	private JPanel issuePanel;
	private List<IssueButtonPanel> issueButtons;
	GridBagLayout grid;
	GridBagConstraints contstraints;
	
	public SwingIssueListView(Mediator mediator) {
		super(mediator, new Dimension(700, 600));

		logoutButton = new JButton("Logout");
		createIssueButton = new JButton("<HTML><body><center>Create<br>Issue</center></body></HTML>");
		filterButton = new JButton("Filter");
		returnButton = new JButton("Return");
		
		grid = new GridBagLayout();
		contstraints = new GridBagConstraints();
		contstraints.fill = GridBagConstraints.HORIZONTAL;
		contstraints.anchor = GridBagConstraints.PAGE_START;
		contstraints.insets = new Insets(5, 5, 5, 5);
		
		issuePanel = new JPanel();
		issuePanel.setLayout(grid);
		issueButtons = new ArrayList<IssueButtonPanel>();
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(issuePanel);
		
		logoutButton.setBounds(550, 30, 100, 40);
		createIssueButton.setBounds(310, 30, 100, 40);
		filterButton.setBounds(430, 30, 100, 40);
		returnButton.setBounds(50, 30, 100, 40);
		scrollPane.setBounds(80, 90, 540, 430);
		
		add(logoutButton);
		add(createIssueButton);
		add(filterButton);
		add(returnButton);
		add(scrollPane);
	}
	
	public void updateIssueButtons(List<Issue> issues, List<ActionListener> listeners) {
		int issueSize = issues.size();
		IssueButtonPanel button;
		
		for(IssueButtonPanel btn : issueButtons) {
			issuePanel.remove(btn);
			issuePanel.revalidate();
			issuePanel.repaint();
		}
		issueButtons.clear();
		
		for(int i = 0; i < issueSize; i++) {
			button = new IssueButtonPanel(issues.get(i), listeners.get(i));
			addGrid(button, 0, i, 1, 1, 1, 0);
			issueButtons.add(button);
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
		issuePanel.add(c);
		issuePanel.revalidate();
		issuePanel.repaint();
	}
	
	public void updateButtonVisibility(boolean isVisible) {
		createIssueButton.setVisible(isVisible);
	}

	public void setCreateIssueListener(ActionListener listener) {
		createIssueButton.addActionListener(listener);
	}
	
	public void setFilterListener(ActionListener listener) {
		filterButton.addActionListener(listener);
	}
	
	@Override
	public void setLogoutListener(ActionListener listener) {
		logoutButton.addActionListener(listener);
	}

	@Override
	public void setReturnListener(ActionListener listener) {
		returnButton.addActionListener(listener);
	}

	@Override
	public String getReturnViewName() { return "ProjectListView"; }

	@Override
	public boolean needNullifyProject() { return true; }

	@Override
	public boolean needNullifyIssue() {	return false; }

	@Override
	public List<String> getAccessableViewNames() {
		List<String> list = new ArrayList<String>();
		list.add("LoginView");
		list.add("ProjectListView");
		list.add("IssueListView");
		list.add("IssueDetailView");
		list.add("IssueCreationView");
		list.add("IssueFilterView");
		return list;
	}

	@Override
	public void refresh() { }

	private class IssueButtonPanel extends JPanel {
		private JButton button;
		private JComboBox<State> stateComboBox;
		private JComboBox<Priority> priorityComboBox;
		private JComboBox<User> reporterComboBox, assigneeComboBox, fixerComboBox;
		
		public IssueButtonPanel(Issue issue, ActionListener listener) {
			super();
			setPreferredSize(new Dimension(500, 40));
			setLayout(null);
			
			button = new JButton(issue.getTitle());
			button.addActionListener(listener);
			stateComboBox = new JComboBox<State>(new State[] { issue.getState() });
			stateComboBox.setEnabled(false);
			priorityComboBox = new JComboBox<Priority>(new Priority[] { issue.getPriority() });
			priorityComboBox.setEnabled(false);
			reporterComboBox = new JComboBox<User>(new User[] { issue.getReporter() });
			reporterComboBox.setEnabled(false);
			assigneeComboBox = new JComboBox<User>(new User[] { issue.getAssignee() });
			assigneeComboBox.setEnabled(false);
			fixerComboBox = new JComboBox<User>(new User[] { issue.getFixer() });
			fixerComboBox.setEnabled(false);

			button.setBounds(0, 0, 150, 40);
			stateComboBox.setBounds(155, 0, 70, 40);
			priorityComboBox.setBounds(230, 0, 70, 40);
			reporterComboBox.setBounds(305, 0, 70, 40);
			assigneeComboBox.setBounds(380, 0, 70, 40);
			fixerComboBox.setBounds(455, 0, 70, 40);
			
			add(button);
			add(stateComboBox);
			add(priorityComboBox);
			add(reporterComboBox);
			add(assigneeComboBox);
			add(fixerComboBox);
		}
	}
}
