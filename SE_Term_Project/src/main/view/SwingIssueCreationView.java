package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;

public class SwingIssueCreationView extends SwingView implements ReturnableView {
	private JLabel infoLabel, titleLabel, stateLabel, priorityLabel, descriptionLabel;
    private JComboBox<State> stateComboBox;
    private JComboBox<Priority> priorityComboBox;
    private JTextField titleTextField, descriptionTextField;
    private JButton createButton, returnButton;
    
	public SwingIssueCreationView(Mediator mediator) {
		super(mediator,  new Dimension(500, 470));

		infoLabel = new JLabel("Create Issue");
		titleLabel = new JLabel("Title");
		stateLabel = new JLabel("State");
		priorityLabel = new JLabel("Priority");
		descriptionLabel = new JLabel("Description");
		titleTextField = new JTextField();
		stateComboBox = new JComboBox<State>(new State[] { State.NEW, State.ASSIGNED, State.FIXED, State.RESOLVED, State.CLOSED, State.REOPENED });
		stateComboBox.setEnabled(false);
		priorityComboBox = new JComboBox<Priority>(new Priority[] {Priority.BLOCKER, Priority.CRITICAL, Priority.MAJOR, Priority.MINOR, Priority.TRIVAL});
		descriptionTextField = new JTextField();
		createButton = new JButton("Create");
		returnButton = new JButton("Return");
		
		infoLabel.setBounds(200, 20, 200, 50);
		
		titleLabel.setBounds(110, 60, 50, 40);
		titleTextField.setBounds(110, 90, 270, 40);
		
		stateLabel.setBounds(110, 130, 50, 40);
		priorityLabel.setBounds(260, 130, 50, 40);
		stateComboBox.setBounds(110, 160, 120, 40);
		priorityComboBox.setBounds(260, 160, 120, 40);
		
		descriptionLabel.setBounds(110, 200, 100, 40);
		descriptionTextField.setBounds(110, 230, 270, 90);
		
		createButton.setBounds(260, 340, 100, 40);
		returnButton.setBounds(140, 340, 100, 40);
		
		add(infoLabel);
		add(titleLabel);
		add(stateLabel);
		add(priorityLabel);
		add(descriptionLabel);
		add(titleTextField);
		add(stateComboBox);
		add(priorityComboBox);
		add(descriptionTextField);
		add(createButton);
		add(returnButton);
	}
	
	public String getTitle() {
		return titleTextField.getText();
	}
	
	public String getDescription() {
		return descriptionTextField.getText();
	}
	
	public Priority getPriority() {
		return (Priority)priorityComboBox.getSelectedItem();
	}

	public void setCreateListener(ActionListener listener) {
		createButton.addActionListener(listener);
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
	public boolean needNullifyIssue() { return false; }

	@Override
	public List<String> getAccessableViewNames() {
		List<String> list = new ArrayList<String>();
		list.add("IssueListView");
		return list;
	}

	@Override
	public void refresh() {
		stateComboBox.setSelectedItem(State.NEW);
		priorityComboBox.setSelectedItem(Priority.MAJOR);
		titleTextField.setText("");
		descriptionTextField.setText("");
	}

}
