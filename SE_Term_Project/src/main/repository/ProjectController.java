package main.repository;

import main.domain.SystemManager;

public class ProjectController {
	private SystemManager systemManager;
	private ProjectRepo repo;
	
	public ProjectController(SystemManager s, ProjectRepo r) {
		this.systemManager = s;
		this.repo = r;
	}
}
