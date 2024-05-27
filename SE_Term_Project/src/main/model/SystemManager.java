package main.model;

import java.util.ArrayList;
import java.util.List;

import main.domain.Issue;
import main.domain.Project;
import main.domain.User;

public class SystemManager {
	private User user;
	private Project project;
	private Issue issue;
	private List<Observer> observers;
	
	public SystemManager() {
		this.user = null;
		this.project = null;
		this.issue = null;
		observers = new ArrayList<Observer>();
	}
	
	//Observer Pattern
	public void subscribe(Observer o) {
		observers.add(o);
	}
	
	public void unsubscribe(Observer o) {
		observers.remove(o);
	}
	
	public void notifyObservers() {
		System.out.println("notifyObservers");
		for(Observer o : observers) o.update();
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public Issue getIssue() {
		return issue;
	}
	
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
}
