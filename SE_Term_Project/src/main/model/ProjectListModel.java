package main.model;

import main.domain.Dev;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.repository.ProjectRepo;

public class ProjectListModel extends Model {
	private SystemManager systemManager;
	private ProjectRepo repo;
	
	public ProjectListModel(SystemManager s, ProjectRepo r) {
		super(s);
		this.repo = r;
	}
	
	public void createProject(String name, ProjectLeader pl, Dev[] devs, Tester[] testers) {
		Project project = new Project(name);
		repo.add(project);
		for(int i = 0; i < devs.length; i++) {
			repo.add(project, devs[i]);
		}
		for(int i = 0; i < testers.length; i++) {
			repo.add(project, testers[i]);
		}
	}
	
//	public Project[] getProjectList() {
//		User user = systemManager.getUser();
//		return repo.find(user);
//	}
}
