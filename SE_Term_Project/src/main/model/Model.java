package main.model;

import java.util.ArrayList;
import java.util.List;

import main.domain.Issue;
import main.domain.Project;
import main.domain.User;

public abstract class Model implements Subject {
	private SystemManager systemManager;
	private List<Observer> observers;
	
	public Model(SystemManager s) {
		this.systemManager = s;
		observers = new ArrayList<Observer>();
	}
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
	
	public void subscribe(Observer o) {
		observers.add(o);
	}
	public void unsubscribe(Observer o) {
		observers.remove(o);
	}
	public void notifyObservers() {
		for(Observer o : observers) o.update();
	}
}
