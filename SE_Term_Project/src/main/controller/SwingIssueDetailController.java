package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import main.domain.Dev;
import main.domain.Issue;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;
import main.model.IssueModel;
import main.model.Observer;
import main.model.ProjectListModel;
import main.view.SwingIssueDetailView;

public class SwingIssueDetailController extends SwingController {
	private ProjectListModel projectListModel;
	private IssueModel issueModel;
	private SwingIssueDetailView view;
	
	public SwingIssueDetailController(SwingIssueDetailView view, ProjectListModel projectListModel, IssueModel issueModel) {
		this.view = view;
		this.projectListModel = projectListModel;
		this.issueModel = issueModel;
		view.setSaveListener(new SaveButtonListener());
		setObserver();
	}
	
	private void setObserver() {
		issueModel.subscribe(new Observer() {
			public void update() {
				if(!view.getAccessableViewNames().contains(view.requestGetCurrentViewName())) return;
				updateComboBoxs();
				updateIssueData();
			}
		});
	}
	
	private void updateComboBoxs() {
		if(projectListModel.getIssue() == null) return;
		List<Dev> devs = new ArrayList<Dev>();
		List<Tester> testers = new ArrayList<Tester>(); 
		
		for(User dev : projectListModel.getAllAccountsInProject(Authority.DEV)) devs.add((Dev)dev);
		for(User tester : projectListModel.getAllAccountsInProject(Authority.TESTER)) testers.add((Tester)tester);
		
		view.updateComboBoxs(devs, testers);
	}
	
	private void updateIssueData() {
		if(issueModel.getIssue() == null) return;
		view.updateIssueData(issueModel.getIssue());
	}
	
	private class SaveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Issue issue = issueModel.getIssue();	
			Priority priority = view.getPriority();
			State state = view.getState();
			Dev assignee = view.getAssignee();
			
			issue.setPriority(priority);
			issue.setState(state);
			issue.setAssignee(assignee);
			issueModel.setIssue(issue);
			issueModel.modify(issue, priority, state, assignee);
			issueModel.notifyObservers();
		}
	}
}
