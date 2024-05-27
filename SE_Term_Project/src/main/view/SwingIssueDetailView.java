package main.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import main.domain.Comment;
import main.domain.Dev;
import main.domain.Issue;
import main.domain.Project;
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
		super(mediator,  new Dimension(700, 850));
		
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
		descriptionTextField.setBounds(180, 170, 320, 70);
		
		reportedDateLabel.setBounds(180, 240, 100, 40);
		reportedDateTextField.setBounds(180, 270, 320, 40);
		
		reporterLabel.setBounds(180, 310, 100, 40);
		reporterComboBox.setBounds(180, 340, 320, 40);
		
		assigneeLabel.setBounds(180, 380, 100, 40);
		assigneeComboBox.setBounds(180, 410, 320, 40);
		
		fixerLabel.setBounds(180, 450, 100, 40);
		fixerComboBox.setBounds(180, 480, 320, 40);
		commentLabel.setBounds(180, 520, 100, 40);
		commentPanel.setBounds(180, 550, 320, 200);
		
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
	
	public Priority getPriority() {
		return (Priority)priorityComboBox.getSelectedItem();
	}
	
	public State getState() {
		return (State)stateComboBox.getSelectedItem();
	}
	
	public Dev getAssignee() {
		return (Dev)assigneeComboBox.getSelectedItem();
	}
	
	public String getCommentContent() {
		return commentPanel.getCommentContent();
	}
	
	public void clearCommentTextField() {
		commentPanel.clearCommentTextField();
	}
	
	public void updateComboBoxs(List<Dev> devs, List<Tester> testers) {
		reporterComboBox.removeAllItems();
		reporterComboBox.addItem(null);
		for(Tester tester : testers) reporterComboBox.addItem(tester); 
		
		assigneeComboBox.removeAllItems();
		assigneeComboBox.addItem(null);
		fixerComboBox.removeAllItems();
		fixerComboBox.addItem(null);
		for(Dev dev : devs) {
			assigneeComboBox.addItem(dev);
			fixerComboBox.addItem(dev); 
		}
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
	
	public void updateComments(List<Comment> comments) {
		commentPanel.updateComments(comments);
	}
	
	public void setWriteListener(ActionListener listener) {
		commentPanel.setWriteListener(listener);
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
		list.add("IssueDetailView");
		list.add("IssueListView");
		return list;
	}

	@Override
	public void refresh() { }
	
	private class CommentPanel extends JPanel {
		private JPanel panel;
		private JTextField commentTextField;
		private JButton writeButton;
	    private JScrollPane scrollPane;
	    
		private GridBagLayout grid;
		private GridBagConstraints contstraints;
		private List<JLabel> commentLabels;
		
		public CommentPanel() {
			setLayout(null);
			setVisible(true);
		
			panel = new JPanel();
			commentTextField = new JTextField();
			writeButton = new JButton("←");
			commentLabels = new ArrayList<JLabel>();
			grid = new GridBagLayout();
			contstraints = new GridBagConstraints();
			contstraints.fill = GridBagConstraints.HORIZONTAL;
			contstraints.anchor = GridBagConstraints.PAGE_START;
			contstraints.insets = new Insets(5, 5, 5, 5);
			panel.setLayout(grid);
			scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(panel);

			scrollPane.setBounds(0, 0, 320, 150);
			commentTextField.setBounds(0, 160, 250, 40);
			writeButton.setBounds(270, 160, 50, 40);
			
			add(scrollPane);
			add(commentTextField);
			add(writeButton);
		}
		
		public String getCommentContent() {
			return commentTextField.getText();
		}
		
		public void clearCommentTextField() {
			commentTextField.setText("");
		}
		
		public void updateComments(List<Comment> comments) {
			JLabel commentLabel;
			String content;
			for(JLabel label : commentLabels) {
				remove(label);
				revalidate();
				repaint();
			}
			commentLabels.clear();
			
			for(int i = 0; i < comments.size(); i++) {
				content = comments.get(i).getContent() + " @" + comments.get(i).getWriter();
				commentLabel = new JLabel(content);
				commentLabel.setPreferredSize(new Dimension(280, 40));
				commentLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				addGrid(commentLabel, 0, i, 1, 1, 1, 0);
				commentLabels.add(commentLabel);
			}
			clearCommentTextField();
		}
		
		public void setWriteListener(ActionListener listener) {
			writeButton.addActionListener(listener);
		}
		
		private void addGrid(Component c, int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty) {
			contstraints.gridx = gridx;
			contstraints.gridy = gridy;
			contstraints.gridwidth = gridwidth;
			contstraints.gridheight = gridheight;
			contstraints.weightx = weightx;
			contstraints.weighty = weighty;
			grid.setConstraints(c, contstraints);
			panel.add(c);
			panel.revalidate();
			panel.repaint();
		}
	}
}
