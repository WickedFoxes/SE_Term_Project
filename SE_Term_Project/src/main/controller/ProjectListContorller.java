package main.controller;

import main.model.ProjectListModel;

abstract public class ProjectListContorller {
	protected ProjectListModel model;
	
	public ProjectListContorller(ProjectListModel model) {
		this.model = model;
	}
}
