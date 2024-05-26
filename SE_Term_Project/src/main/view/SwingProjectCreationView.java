package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import main.domain.Dev;
import main.domain.ProjectLeader;
import main.domain.Tester;

public class SwingProjectCreationView extends SwingView implements ReturnableView{
	private JLabel infoLabel, nameLabel, plLabel, devLabel, testerLabel;
    private JTextField nameTextField;
    private JList<ProjectLeader> plList;
    private JList<Dev> devList;
    private JList<Tester> testerList;
    private JButton createButton, returnButton;
    private JScrollPane plScrollPane, devScrollPane, testerScrollPane; 
    
	public SwingProjectCreationView(Mediator mediator) {
		super(mediator, new Dimension(500, 550));
		
		infoLabel = new JLabel("Create Project");
		nameLabel = new JLabel("Name");
		plLabel = new JLabel("PL");
		devLabel = new JLabel("Devs");
		testerLabel = new JLabel("Testers");
		nameTextField = new JTextField();
		plList = new JList<ProjectLeader>();
		plList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		devList = new JList<Dev>();
		testerList = new JList<Tester>();
        createButton = new JButton("Create");
        returnButton = new JButton("Return");
        plScrollPane = new JScrollPane();
        plScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        plScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        plScrollPane.setViewportView(plList);
        devScrollPane = new JScrollPane();
        devScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        devScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        devScrollPane.setViewportView(devList);
        testerScrollPane = new JScrollPane();
        testerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        testerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        testerScrollPane.setViewportView(testerList);
        
		infoLabel.setBounds(200, 20, 200, 50);
		nameLabel.setBounds(90, 70, 50, 50);
		plLabel.setBounds(90, 130, 50, 50);
		devLabel.setBounds(90, 190, 50, 50);
		testerLabel.setBounds(90, 300, 50, 50);
		nameTextField.setBounds(150, 70, 200, 50);
		plScrollPane.setBounds(150, 130, 200, 50);
        devScrollPane.setBounds(150, 190, 200, 100);
        testerScrollPane.setBounds(150, 300, 200, 100);
        createButton.setBounds(260, 420, 100, 40);
        returnButton.setBounds(140, 420, 100, 40);

        add(infoLabel);
        add(nameLabel);
        add(plLabel);
        add(devLabel);
        add(testerLabel);
        add(nameTextField);
        add(plScrollPane);
        add(devScrollPane);
        add(testerScrollPane);
        add(createButton);
        add(returnButton);
	}
	
	public String getName() {
		return nameTextField.getText();
	}
	
	public ProjectLeader getPL() {
		return plList.getSelectedValue();
	}
	
	public List<Dev> getDevs() {
		return devList.getSelectedValuesList();
	}
	
	public List<Tester> getTesters() {
		return testerList.getSelectedValuesList();
	}
	 
	public void setPLList(List<ProjectLeader> pls) {		
		DefaultListModel<ProjectLeader> plModel = new DefaultListModel<ProjectLeader>();
		plModel.addAll(pls);
		plList.setModel(plModel);
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
	
	public void setCreateListener(ActionListener listener) {
		createButton.addActionListener(listener);
	}
	
	@Override
	public void setReturnListener(ActionListener listener) {
		returnButton.addActionListener(listener);
	}
	
	@Override
	public String getReturnViewName() { return "ProjectListView"; }
	
	@Override
	public boolean needNullifyProject() { return false; }

	@Override
	public boolean needNullifyIssue() {	return false; }
	
	@Override
	public List<String> getAccessableViewNames() {
		List<String> list = new ArrayList<String>();
		list.add("ProjectListView");
		return list;
	}

	@Override
	public void refresh() {
		nameTextField.setText("");
		plList.clearSelection();
		devList.clearSelection();
		testerList.clearSelection();
	}
}
