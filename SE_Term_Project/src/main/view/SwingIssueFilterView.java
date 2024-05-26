package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import main.domain.Dev;
import main.domain.FilterOption;
import main.domain.Tester;
import main.domain.enumeration.State;

public class SwingIssueFilterView extends SwingView {
	private JLabel infoLabel, stateLabel, assigneeLabel, reporterLabel;
	private JComboBox<State> stateComboBox;
    private JComboBox<Dev> assigneeComboBox;
    private JComboBox<Tester> reporterComboBox;
	private JButton applyButton;
	
	public SwingIssueFilterView(Mediator mediator) {
        super(mediator, new Dimension(500, 450));
        
        infoLabel = new JLabel("Issue Filter");
        stateLabel = new JLabel("State");
        assigneeLabel = new JLabel("Assignee");
        reporterLabel  = new JLabel("Reporter");
        stateComboBox = new JComboBox<State>(new State[] { State.NEW, State.ASSIGNED, State.FIXED, State.RESOLVED, State.CLOSED, State.REOPENED });
        stateComboBox.insertItemAt(null, 0);
        assigneeComboBox = new JComboBox<Dev>();
        reporterComboBox = new JComboBox<Tester>();
        applyButton = new JButton("Apply");
        
        infoLabel.setBounds(200, 20, 200, 40);
        stateLabel.setBounds(130, 50, 50, 40);
        stateComboBox.setBounds(130, 80, 200, 50);
        
        reporterLabel.setBounds(130, 130, 50, 40);
        reporterComboBox.setBounds(130, 160, 200, 50);
        
        assigneeLabel.setBounds(130, 210, 100, 40);
        assigneeComboBox.setBounds(130, 240, 200, 50);
        applyButton.setBounds(130, 310, 200, 50);
        
        add(infoLabel);
        add(stateLabel);
        add(assigneeLabel);
        add(reporterLabel);
        add(stateComboBox);
        add(assigneeComboBox);
        add(reporterComboBox);
        add(applyButton);
	}
	
	public void updateFilterOptionSelection(FilterOption option) {
		stateComboBox.setSelectedItem(option.getState());
		assigneeComboBox.setSelectedItem(option.getAssignee());
		reporterComboBox.setSelectedItem(option.getReporter());
	}
	
	public FilterOption getFilterOption() {
		State state = (stateComboBox.getSelectedIndex() >= 0)? (State)stateComboBox.getSelectedItem(): null;
		Tester tester = (reporterComboBox.getSelectedIndex() >= 0)? (Tester)reporterComboBox.getSelectedItem(): null;
		Dev dev = (assigneeComboBox.getSelectedIndex() >= 0)? (Dev)assigneeComboBox.getSelectedItem(): null;
		return new FilterOption(state, tester, dev);
	}
	
	public void setApplyListener(ActionListener listener) {
		applyButton.addActionListener(listener);
	}
	
	public void updateAssigneeComboBox(List<Dev> devs) {
		assigneeComboBox.removeAllItems();
		assigneeComboBox.addItem(null);
		for(Dev dev : devs) assigneeComboBox.addItem(dev); 
	}
	
	public void updateReporterComboBox(List<Tester> testers) {
		reporterComboBox.removeAllItems();
		reporterComboBox.addItem(null);
		for(Tester tester : testers) reporterComboBox.addItem(tester); 
	}

	@Override
	public List<String> getAccessableViewNames() {
		List<String> list = new ArrayList<String>();
		list.add("IssueListView");
		return list;
	}

	@Override
	public void refresh() {	}
}
