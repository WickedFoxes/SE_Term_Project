package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.model.LoginModel;
import main.model.ProjectListModel;
import main.view.SwingProjectListView;

public class SwingProjectListController extends SwingController {
	private ProjectListModel model;
	private SwingProjectListView view;
	
	public SwingProjectListController(SwingProjectListView view, ProjectListModel model) {
		this.view = view;
		this.model = model;
	}
	
	private class CreateAccountListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}
	
	private class CreateProjectListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}
}
