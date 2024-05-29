package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
		view.setSaveListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveIssue();
			}
		});
		view.setStateComboBoxListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(issueModel.getUser().getAuthority() == Authority.DEV && view.getState() == State.FIXED) {
					view.updateFixer((Dev)issueModel.getUser());
				}
			}
		});
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
		view.updateIssueData(issueModel.getIssue(), issueModel.getUser().getAuthority());
	}
	
	private void saveIssue() {
		Issue currentIssue = issueModel.getIssue();	
		Priority priority = view.getPriority();
		State state = view.getState();
		Dev assignee = view.getAssignee();
					
		boolean sucess = issueModel.tryModify(currentIssue, priority, state, assignee);
		if(sucess) {
			currentIssue.setPriority(priority);
			currentIssue.setState(state);
			currentIssue.setAssignee(assignee);
			issueModel.setIssue(currentIssue);
			issueModel.notifyObservers();
			
			view.showMessagePopup("Save Complete", "저장이 완료되었습니다.", JOptionPane.INFORMATION_MESSAGE);
		}
		else view.showMessagePopup("Save Error", "저장에 실패했습니다.", JOptionPane.ERROR_MESSAGE);
	}
}
