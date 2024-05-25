package main.model;

import java.util.ArrayList;
import java.util.List;

import main.domain.Issue;
import main.domain.Project;
import main.domain.User;

public abstract class Model {
	private SystemManager systemManager;
	
	public Model(SystemManager s) {
		this.systemManager = s;
	}
	
	//Observer Pattern
	public void subscribe(Observer o) {
		systemManager.subscribe(o);
	}
	public void unsubscribe(Observer o) {
		systemManager.unsubscribe(o);
	}
	public void notifyObservers() {
		systemManager.notifyObservers();
	}

	//getter & setter
	public User getUser() {
		return systemManager.getUser();
	}
	public void setUser(User user) {
		systemManager.setUser(user);
	}
	public Project getProject() {
		return systemManager.getProject();
	}
	public void setProject(Project project) {
		systemManager.setProject(project);
	}
	public Issue getIssue() {
		return systemManager.getIssue();
	}
	public void setIssue(Issue issue) {
		systemManager.setIssue(issue);
	}
}
