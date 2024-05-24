package main.model;

import java.util.List;

import main.domain.Dev;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.repository.ProjectRepo;

public class ProjectListModel extends Model {
	private ProjectRepo repo;
	
	public ProjectListModel(SystemManager s, ProjectRepo r) {
		super(s);
		this.repo = r;
	}
	
	public boolean createProject(String name, ProjectLeader pl, List<Dev> devs, List<Tester> testers) {
		if(super.systemManager.getUser() == null) return false;
		if(super.systemManager.getUser().getAuthority() != Authority.ADMIN) return false;
		
		Project project = new Project(name);
		project = repo.add(project);
		
		repo.add(project, pl);
		for(Dev dev : devs) {
			repo.add(project, dev);
		}
		for(Tester tester : testers) {
			repo.add(project, tester);
		}
		return true;
	}
	
	public List<Project> getProjectList() {
		User user = systemManager.getUser();
		return repo.findAll(user);
	}
	
	public User getUser() {
		return systemManager.getUser();
	}
}
