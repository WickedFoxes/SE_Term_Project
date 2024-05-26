package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.domain.Dev;
import main.domain.Issue;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;
import main.model.IssueModel;
import main.model.Observer;
import main.view.SwingIssueDetailView;

public class SwingIssueDetailController extends SwingController {
	private IssueModel model;
	private SwingIssueDetailView view;
	
	public SwingIssueDetailController(SwingIssueDetailView view, IssueModel model) {
		this.view = view;
		this.model = model;
		view.setSaveListener(new SaveButtonListener());
		setObserver();
	}
	
	private void setObserver() {
		model.subscribe(new Observer() {
			public void update() {
				if(model.getIssue() != null) view.updateIssueData(model.getIssue());
			}
		});
	}
	
	private class SaveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Issue issue = model.getIssue();	
			Priority priority = view.getPriority();
			State state = view.getState();
			Dev assignee = view.getAssignee();
			
			issue.setPriority(priority);
			issue.setState(state);
			issue.setAssignee(assignee);
			model.modify(issue, priority, state, assignee);
			model.notifyObservers();
		}
	}
}
