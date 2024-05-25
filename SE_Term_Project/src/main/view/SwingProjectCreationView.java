package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import main.domain.Dev;
import main.domain.ProjectLeader;
import main.domain.Tester;

public class SwingProjectCreationView extends SwingView implements ReturnableView{
	private JLabel infoLabel, nameLabel, plLabel, devLabel, testerLabel;
    private JTextField nameTextField;
    private JComboBox<ProjectLeader> plComboBox;
    private JList<Dev> devList;
    private JList<Tester> testerList;
    private JButton createButton, returnButton;
    private DefaultListModel<Dev> devModel;
    private DefaultListModel<Tester> testerModel;
    
	public SwingProjectCreationView(Mediator mediator) {
		super(mediator, new Dimension(500, 450));
		
		infoLabel = new JLabel("Create Project");
		nameLabel = new JLabel("Name");
		plLabel = new JLabel("PL");
		devLabel = new JLabel("Devs");
		testerLabel = new JLabel("Testers");
		nameTextField = new JTextField();
		plComboBox = new JComboBox<ProjectLeader>();
		devList = new JList<Dev>();
		testerList = new JList<Tester>();
        createButton = new JButton("Create");
        returnButton = new JButton("Return");
		
		infoLabel.setBounds(200, 20, 200, 50);
		nameLabel.setBounds(100, 70, 50, 50);
		plLabel.setBounds(100, 130, 50, 50);
		devLabel.setBounds(100, 190, 50, 50);
		testerLabel.setBounds(100, 250, 50, 50);
		nameTextField.setBounds(150, 70, 200, 50);
		plComboBox.setBounds(150, 130, 200, 50);
		devList.setBounds(150, 190, 200, 50);
		testerList.setBounds(150, 250, 200, 50);
        createButton.setBounds(140, 320, 100, 40);
        returnButton.setBounds(260, 320, 100, 40);
    
        add(infoLabel);
        add(nameLabel);
        add(nameTextField);
        add(plLabel);
        add(plComboBox);
        add(devLabel);
        add(devList);
        add(testerLabel);
        add(testerList);
        add(createButton);
        add(returnButton);
	}
	
	public String getName() {
		return nameTextField.getText();
	}
	
	public ProjectLeader getPL() {
		return (ProjectLeader)plComboBox.getSelectedItem();
	}
	
	public List<Dev> getDevs() {
		return devList.getSelectedValuesList();
	}
	
	public List<Tester> getTesters() {
		return testerList.getSelectedValuesList();
	}
	 
	public void setPLComboBox(List<ProjectLeader> pls) {
		ProjectLeader[] arr = new ProjectLeader[pls.size()];
		for(int i = 0; i < pls.size(); i++) arr[i] = pls.get(i);
		plComboBox = new JComboBox<ProjectLeader>(arr);
	}
	
	public void setDevList(List<Dev> devs) {
		devModel = new DefaultListModel<Dev>();
		devModel.addAll(devs);
		devList.setModel(devModel);
	}
	
	public void setTesterList(List<Tester> testers) {
		testerModel = new DefaultListModel<Tester>();
		testerModel.addAll(testers);
		testerList.setModel(testerModel);
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
		plComboBox.setSelectedIndex(-1);
		devList.setSelectedIndex(-1);
		testerList.setSelectedIndex(-1);
	}
}
