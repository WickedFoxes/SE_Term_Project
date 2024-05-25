package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.model.ProjectListModel;
import main.view.SwingProjectCreationView;

public class SwingProjectCreationController extends SwingController {
	private SwingProjectCreationView view;
	private ProjectListModel model;
	
	public SwingProjectCreationController(SwingProjectCreationView view, ProjectListModel model) {
		this.model = model;
		this.view = view;
	}
	

	
}
