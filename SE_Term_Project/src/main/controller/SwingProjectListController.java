package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.domain.Admin;
import main.domain.Project;
import main.model.LoginModel;
import main.model.Observer;
import main.model.ProjectListModel;
import main.view.SwingProjectListView;

public class SwingProjectListController extends SwingController {
	private SwingProjectListView view;
	private ProjectListModel model;
	
	public SwingProjectListController(SwingProjectListView view, ProjectListModel model) {
		this.model = model;
		this.view = view;
		view.setCreateAccountListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.requestChangeView("AccountCreationView");
			}
		});
		view.setCreateProjectListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.requestChangeView("ProjectCreationView");
			}
		});
		setObserver();
	}
	
	private void setObserver() {
		model.subscribe(new Observer() {
			public void update() {
				if(!view.getAccessableViewNames().contains(view.requestGetCurrentViewName())) return;
				updateProjectButtons();
				updateButtonVisiblities();
			}
		});
	}
	
	private void updateButtonVisiblities() {
		if(model.getUser() == null) return;
		boolean isAdmin = (model.getUser() instanceof Admin);
		view.updateButtonVisibilities(isAdmin);
	}
	
	private void updateProjectButtons() {
		if(model.getUser() == null) return;
		List<Project> projects = model.getProjectList();
		List<ActionListener> listeners = new ArrayList<ActionListener>();
		int n = projects.size();
		Project project;

		for(int i = 0; i < n; i++) {
			project = projects.get(i);
			listeners.add(new ProjectButtonListener(project));
		}
		view.updateProjectButtons(projects, listeners);
	}
	
	private class ProjectButtonListener implements ActionListener{
		private Project project;
		public ProjectButtonListener(Project project) {
			this.project = project;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setProject(project);
			model.notifyObservers();
			view.requestChangeView("IssueListView");
		}
	}
}
