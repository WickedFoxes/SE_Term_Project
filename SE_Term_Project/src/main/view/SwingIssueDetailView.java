package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.domain.Dev;
import main.domain.Issue;
import main.domain.Tester;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;

public class SwingIssueDetailView extends SwingView implements ReturnableView {
	private JLabel stateLabel, priorityLabel, descriptionLabel, reportedDateLabel, reporterLabel, assigneeLabel, fixerLabel, commentLabel;
    private JComboBox<State> stateComboBox;
    private JComboBox<Priority> priorityComboBox;
    private JTextField titleTextField, descriptionTextField, reportedDateTextField;
    private JComboBox<Tester> reporterComboBox;
    private JComboBox<Dev> assigneeComboBox, fixerComboBox;
    private JButton returnButton, saveButton;
    private CommentPanel commentPanel;
	
	public SwingIssueDetailView(Mediator mediator) {
		super(mediator,  new Dimension(700, 800));
		
		stateLabel = new JLabel("State");
		priorityLabel = new JLabel("Priority");
		descriptionLabel = new JLabel("Description");
		reportedDateLabel = new JLabel("Reported Date");
		reporterLabel = new JLabel("Reporter");
		assigneeLabel = new JLabel("Assignee");
		fixerLabel = new JLabel("Fixer");
		commentLabel = new JLabel("Comment");
		stateComboBox = new JComboBox<State>(new State[] { State.NEW, State.ASSIGNED, State.FIXED, State.RESOLVED, State.CLOSED, State.REOPENED });
		priorityComboBox = new JComboBox<Priority>(new Priority[] {Priority.BLOCKER, Priority.CRITICAL, Priority.MAJOR, Priority.MINOR, Priority.TRIVAL});
		titleTextField = new JTextField(); 
		titleTextField.setEnabled(false);
		descriptionTextField = new JTextField();
		descriptionTextField.setEnabled(false);
		reportedDateTextField = new JTextField();
		reportedDateTextField.setEnabled(false);
		reporterComboBox = new JComboBox<Tester>();
		assigneeComboBox = new JComboBox<Dev>();
		fixerComboBox = new JComboBox<Dev>();
		commentPanel = new CommentPanel();
		returnButton = new JButton("Return");
		saveButton = new JButton("Save");
		
		titleTextField.setBounds(180, 30, 320, 40);
		stateLabel.setBounds(180, 70, 50, 40);
		priorityLabel.setBounds(340, 70, 50, 40);
		stateComboBox.setBounds(180, 100, 140, 40);
		priorityComboBox.setBounds(340, 100, 160, 40);
		
		descriptionLabel.setBounds(180, 140, 100, 40);
		descriptionTextField.setBounds(180, 170, 320, 90);
		
		reportedDateLabel.setBounds(180, 260, 100, 40);
		reportedDateTextField.setBounds(180, 290, 320, 40);
		
		reporterLabel.setBounds(180, 330, 100, 40);
		reporterComboBox.setBounds(180, 360, 320, 40);
		
		assigneeLabel.setBounds(180, 400, 100, 40);
		assigneeComboBox.setBounds(180, 430, 320, 40);
		
		fixerLabel.setBounds(180, 470, 100, 40);
		fixerComboBox.setBounds(180, 500, 320, 40);
		commentLabel.setBounds(180, 540, 100, 40);
		commentPanel.setBounds(180, 570, 320, 200);
		
		returnButton.setBounds(50, 30, 100, 40);
		saveButton.setBounds(50, 80, 100, 40);
		
		add(stateLabel);
		add(priorityLabel);
		add(descriptionLabel);
		add(reportedDateLabel);
		add(reporterLabel);
		add(assigneeLabel);
		add(fixerLabel);
		add(commentLabel);
		add(stateComboBox);
		add(priorityComboBox);
		add(titleTextField);
		add(descriptionTextField);
		add(reportedDateTextField);
		add(reporterComboBox);
		add(assigneeComboBox);
		add(fixerComboBox);
		add(commentPanel);
		add(returnButton);
		add(saveButton);
	}
	
	public void updateIssueData(Issue issue) {
		titleTextField.setText(issue.getTitle());
		descriptionTextField.setText(issue.getDescription());
		reportedDateTextField.setText(issue.getReportedDate().toGMTString());
		stateComboBox.setSelectedItem(issue.getState());
		priorityComboBox.setSelectedItem(issue.getPriority());
		reporterComboBox.setSelectedItem(issue.getReporter());
		assigneeComboBox.setSelectedItem(issue.getAssignee());
		fixerComboBox.setSelectedItem(issue.getFixer());
	}
	
	public Priority getPriority() {
		return (Priority)priorityComboBox.getSelectedItem();
	}
	
	public State getState() {
		return (State)stateComboBox.getSelectedItem();
	}
	
	public Dev getAssignee() {
		return (Dev)assigneeComboBox.getSelectedItem();
	}

	public void setSaveListener(ActionListener listener) {
		saveButton.addActionListener(listener);
	}
	
	@Override
	public void setReturnListener(ActionListener listener) {
		returnButton.addActionListener(listener);
	}

	@Override
	public String getReturnViewName() { return "IssueListView"; }

	@Override
	public boolean needNullifyProject() { return false; }

	@Override
	public boolean needNullifyIssue() { return true; }

	@Override
	public List<String> getAccessableViewNames() { 
		List<String> list = new ArrayList<String>();
		list.add("IssueListView");
		return list;
	}

	@Override
	public void refresh() { }
	
	private class CommentPanel extends JPanel {
		
	}
}
