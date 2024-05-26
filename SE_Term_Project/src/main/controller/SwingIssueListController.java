package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import main.domain.Admin;
import main.domain.FilterOption;
import main.domain.Issue;
import main.domain.Project;
import main.domain.Tester;
import main.model.IssueListModel;
import main.model.Observer;
import main.view.SwingIssueFilterView;
import main.view.SwingIssueListView;

public class SwingIssueListController extends SwingController {
	private SwingIssueListView view;
	private SwingIssueFilterView filterView;
	private IssueListModel model;
	
	public SwingIssueListController(SwingIssueListView view, IssueListModel model) {
		this.model = model;
		this.view = view;
		this.filterView = new SwingIssueFilterView(view);
		
		view.setCreateIssueListener(new CreateIssueButtonListener());
		view.setFilterListener(new FilterButtonListener());
		setObserver();
	}
	
	private void setObserver() {
		model.subscribe(new Observer() {
			public void update() {
				setIssueButtons();
				setCreateIssueButtonVisibility();
			}
		});
	}
	
	private void setIssueButtons() {
		if(model.getUser() == null) return;
		if(model.getProject() == null) return;
		
		FilterOption filterOption = filterView.getFilterOption();
		List<Issue> issues = model.getIssueList(filterOption);
		List<ActionListener> listeners = new ArrayList<ActionListener>();
		int n = issues.size();
		Issue issue;
		
		for(int i = 0; i < n; i++) {
			issue = issues.get(i);
			listeners.add(new IssueButtonListener(issue));
		}
		view.setIssueButtons(issues, listeners);
	}
	
	private void setCreateIssueButtonVisibility() {
		boolean isTester = (model.getUser() instanceof Tester);
		view.setCreateIssueButtonVisibility(isTester);
	}
	
	private class CreateIssueButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			view.requestChangeView("IssueCreationView");
		}
	}
	
	private class FilterButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			filterView.showPopup();
		}
	}
	
	private class IssueButtonListener implements ActionListener{
		private Issue issue;
		public IssueButtonListener(Issue issue) {
			this.issue = issue;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setIssue(issue);
			view.requestChangeView("IssueDetailView");
		}
	}
}
