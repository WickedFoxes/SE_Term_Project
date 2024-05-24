package main.model;

import main.domain.Issue;
import main.domain.Project;
import main.domain.User;

public class SystemManager {
	private User user;
	private Project project;
	private Issue issue;
	
	public SystemManager() {
		this.user = null;
		this.project = null;
		this.issue = null;
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