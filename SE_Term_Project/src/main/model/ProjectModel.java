package main.model;

import main.domain.SystemManager;
import main.repository.ProjectRepo;

public class ProjectModel extends Model {
	private SystemManager systemManager;
	private ProjectRepo repo;
	
	public ProjectModel(SystemManager s, ProjectRepo r) {
		super(s);
		this.repo = r;
	}
}
