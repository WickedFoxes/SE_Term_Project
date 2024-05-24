package main.model;

import main.domain.Issue;
import main.domain.Project;
import main.domain.User;

public abstract class Model {
	private SystemManager systemManager;
	
	public Model(SystemManager s) {
		this.systemManager = s;
	}
	public User getUser() {
		return systemManager.getUser();
	}
	protected void setUser(User user) {
		systemManager.setUser(user);
	}
	public Project getProject() {
		return systemManager.getProject();
	}
	protected void setProject(Project project) {
		systemManager.setProject(project);
	}
	public Issue getIssue() {
		return systemManager.getIssue();
	}
	protected void setIssue(Issue issue) {
		systemManager.setIssue(issue);
	}
}
