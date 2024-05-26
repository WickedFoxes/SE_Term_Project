package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
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

public class SwingIssueFilterView extends JFrame {
	private JLabel infoLabel, stateLabel, devLabel, testerLabel;
	private JComboBox<State> stateComboBox;
    private JList<Dev> devList;
    private JList<Tester> testerList;
	private JButton applyButton;
	private JScrollPane devScrollPane, testerScrollPane;
	
	public SwingIssueFilterView(SwingView view) {
        setSize(new Dimension(500, 450));
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(view);
        
        infoLabel = new JLabel("Issue Filter");
        stateLabel = new JLabel("State");
        devLabel = new JLabel("Assignee");
        testerLabel  = new JLabel("Reporter");
        stateComboBox = new JComboBox<State>(new State[] { State.NEW, State.ASSIGNED, State.FIXED, State.RESOLVED, State.CLOSED, State.REOPENED });
        stateComboBox.insertItemAt(null, 0);
		devList = new JList<Dev>();
		devList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		testerList = new JList<Tester>();
		testerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        devScrollPane = new JScrollPane();
        devScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        devScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        devScrollPane.setViewportView(devList);
        testerScrollPane = new JScrollPane();
        testerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        testerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        testerScrollPane.setViewportView(testerList);
        applyButton = new JButton("Apply");
        
        infoLabel.setBounds(200, 20, 200, 40);
        stateLabel.setBounds(130, 50, 50, 40);
        stateComboBox.setBounds(130, 80, 200, 50);
        
        testerLabel.setBounds(130, 130, 50, 40);
        testerScrollPane.setBounds(130, 160, 200, 50);
        
        devLabel.setBounds(130, 210, 50, 40);
        devScrollPane.setBounds(130, 240, 200, 50);
        applyButton.setBounds(130, 310, 200, 50);
        
        add(infoLabel);
        add(stateLabel);
        add(devLabel);
        add(testerLabel);
        add(stateComboBox);
        add(devScrollPane);
        add(testerScrollPane);
        add(applyButton);
	}
	
	public void showPopup() {
		setVisible(true);
		refresh();
	}
	
	public FilterOption getFilterOption() {
		State state = (stateComboBox.getSelectedIndex() >= 0)? (State)stateComboBox.getSelectedItem(): null;
		Tester tester = (devList.getSelectedIndex() >= 0)? (Tester)testerList.getSelectedValue(): null;
		Dev dev = (devList.getSelectedIndex() >= 0)? (Dev)devList.getSelectedValue(): null;
		return new FilterOption(state, tester, dev);
	}
	
	private void refresh() {
		stateComboBox.setSelectedIndex(0);
	    devList.clearSelection();
	    testerList.clearSelection();
	}
	
	public void setApplyListener(ActionListener listener) {
		applyButton.addActionListener(listener);
	}
	
	public void setDevList(List<Dev> devs) {
		DefaultListModel<Dev> devModel = new DefaultListModel<Dev>();
		devModel.addAll(devs);
		devList.setModel(devModel);
	}
	
	public void setTesterList(List<Tester> testers) {
		DefaultListModel<Tester> testerModel = new DefaultListModel<Tester>();
		testerModel.addAll(testers);
		testerList.setModel(testerModel);
	}

}
