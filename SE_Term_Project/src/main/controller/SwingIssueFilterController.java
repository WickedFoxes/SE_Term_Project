package main.controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.domain.Dev;
import main.domain.FilterOption;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.model.IssueListModel;
import main.model.Observer;
import main.model.ProjectListModel;
import main.view.SwingIssueFilterView;

public class SwingIssueFilterController extends SwingController {
	private SwingIssueFilterView view; 
	private ProjectListModel projectListModel;
	private IssueListModel issueListModel;
	
	
	public SwingIssueFilterController(SwingIssueFilterView view, ProjectListModel projectListModel, IssueListModel issueListModel) {
		this.projectListModel = projectListModel;
		this.issueListModel = issueListModel;
		this.view = view;
		this.view.setApplyListener(new ApplyButtonListener());
		setObserver();
	}
	
	private void setObserver() {
		issueListModel.subscribe(new Observer() {
			public void update() {
				if(!view.getAccessableViewNames().contains(view.requestGetCurrentViewName())) return;
				updateFilterOptionSelection();
				updateComboBoxs();
			}
		});
	}
	
	private void updateFilterOptionSelection() {
		if(issueListModel.getProject() == null) return;
		FilterOption option = issueListModel.getFilterOption();
		view.updateFilterOptionSelection(option);
	}
	
	private void updateComboBoxs() {
		if(projectListModel.getProject() == null) return;
		List<Dev> devs = new ArrayList<Dev>();
		List<Tester> testers = new ArrayList<Tester>(); 
		
		for(User dev : projectListModel.getAllAccountsInProject(Authority.DEV)) devs.add((Dev)dev);
		for(User tester : projectListModel.getAllAccountsInProject(Authority.TESTER)) testers.add((Tester)tester);
		
		view.updateComboBoxs(devs, testers);
	}
	
	private class ApplyButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			FilterOption option = view.getFilterOption();
			issueListModel.setFilterOption(option);
			issueListModel.notifyObservers();
			view.requestChangeView("IssueListView");
			
			Window window = SwingUtilities.getWindowAncestor(view);
	        if (window instanceof JFrame) {
	            ((JFrame)window).setVisible(false);
	        }
	        
	        System.out.println("Filter Applied :" + option.getState() +", "+option.getAssignee() +", "+option.getReporter() );
		}
	}
}
