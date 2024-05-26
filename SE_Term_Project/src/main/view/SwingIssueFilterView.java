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
    private JList<Dev> assigneeList;
    private JList<Tester> reporterList;
	private JButton applyButton;
	private JScrollPane assigneeScrollPane, reporterScrollPane;
	
	public SwingIssueFilterView(Mediator mediator) {
        super(mediator, new Dimension(500, 450));
        
        infoLabel = new JLabel("Issue Filter");
        stateLabel = new JLabel("State");
        assigneeLabel = new JLabel("Assignee");
        reporterLabel  = new JLabel("Reporter");
        stateComboBox = new JComboBox<State>(new State[] { State.NEW, State.ASSIGNED, State.FIXED, State.RESOLVED, State.CLOSED, State.REOPENED });
        stateComboBox.insertItemAt(null, 0);
        assigneeList = new JList<Dev>();
        assigneeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reporterList = new JList<Tester>();
        reporterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		assigneeScrollPane = new JScrollPane();
		assigneeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		assigneeScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        assigneeScrollPane.setViewportView(assigneeList);
        reporterScrollPane = new JScrollPane();
        reporterScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        reporterScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        reporterScrollPane.setViewportView(reporterList);
        applyButton = new JButton("Apply");
        
        infoLabel.setBounds(200, 20, 200, 40);
        stateLabel.setBounds(130, 50, 50, 40);
        stateComboBox.setBounds(130, 80, 200, 50);
        
        reporterLabel.setBounds(130, 130, 50, 40);
        reporterScrollPane.setBounds(130, 160, 200, 50);
        
        assigneeLabel.setBounds(130, 210, 100, 40);
        assigneeScrollPane.setBounds(130, 240, 200, 50);
        applyButton.setBounds(130, 310, 200, 50);
        
        add(infoLabel);
        add(stateLabel);
        add(assigneeLabel);
        add(reporterLabel);
        add(stateComboBox);
        add(assigneeScrollPane);
        add(reporterScrollPane);
        add(applyButton);
	}
	
	public void setCurrentFilterOptionSelection(FilterOption option) {
		stateComboBox.setSelectedItem(option.getState());
		assigneeList.setSelectedValue(option.getAssignee(), false);
		reporterList.setSelectedValue(option.getReporter(), false);
	}
	
	public FilterOption getFilterOption() {
		State state = (stateComboBox.getSelectedIndex() >= 0)? (State)stateComboBox.getSelectedItem(): null;
		Tester tester = (assigneeList.getSelectedIndex() >= 0)? (Tester)reporterList.getSelectedValue(): null;
		Dev dev = (assigneeList.getSelectedIndex() >= 0)? (Dev)assigneeList.getSelectedValue(): null;
		return new FilterOption(state, tester, dev);
	}
	
	public void setApplyListener(ActionListener listener) {
		applyButton.addActionListener(listener);
	}
	
	public void setAssigneeList(List<Dev> devs) {
		DefaultListModel<Dev> devModel = new DefaultListModel<Dev>();
		devModel.addAll(devs);
		assigneeList.setModel(devModel);
	}
	
	public void setReporterList(List<Tester> testers) {
		DefaultListModel<Tester> testerModel = new DefaultListModel<Tester>();
		testerModel.addAll(testers);
		reporterList.setModel(testerModel);
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
