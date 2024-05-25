package main.model;

import java.util.ArrayList;
import java.util.List;

import main.domain.Issue;
import main.domain.Project;
import main.domain.User;

public abstract class Model {
	private SystemManager systemManager;
	private List<Observer> observers;
	
	public Model(SystemManager s) {
		this.systemManager = s;
		observers = new ArrayList<Observer>();
		System.out.println("observers init"+ this);
	}
	
	//Observer Pattern
	public void subscribe(Observer o) {
		observers.add(o);
		System.out.println(observers + "subscribed " + observers.size() + ", " + observers.get(0));
	}
	public void unsubscribe(Observer o) {
		observers.remove(o);
	}
	public void notifyObservers() {
		System.out.println(observers + "notify to " + observers.size());
		for(Observer o : observers) o.update();
	}
	
	//getter & setter
	public User getUser() {
		return systemManager.getUser();
	}
	public void setUser(User user) {
		systemManager.setUser(user);
		notifyObservers();
	}
	public Project getProject() {
		return systemManager.getProject();
	}
	public void setProject(Project project) {
		systemManager.setProject(project);
		notifyObservers();
	}
	public Issue getIssue() {
		return systemManager.getIssue();
	}
	public void setIssue(Issue issue) {
		systemManager.setIssue(issue);
		notifyObservers();
	}
}
