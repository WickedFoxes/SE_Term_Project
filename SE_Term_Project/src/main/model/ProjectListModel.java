package main.model;

import java.util.List;

import main.domain.*;
import main.domain.enumeration.Authority;
import main.repository.ProjectRepo;
import main.repository.AccountRepo;

public class ProjectListModel extends Model {
	private ProjectRepo project_repo;
	private AccountRepo account_repo;
	
	public ProjectListModel(SystemManager s, ProjectRepo p, AccountRepo a) {
		super(s);
		this.project_repo = p;
		this.account_repo = a;
	}
	
	public boolean tryCreateProject(String name, ProjectLeader pl, List<Dev> devs, List<Tester> testers) {
		if(getUser() == null) return false;
		if(getUser().getAuthority() != Authority.ADMIN) return false;
		if(name == "") return false;
		
		Project project = new Project(name);
		project = project_repo.add(project);
		
		project_repo.add(project, pl);
		for(Dev dev : devs) {
			project_repo.add(project, dev);
		}
		for(Tester tester : testers) {
			project_repo.add(project, tester);
		}
		notifyObservers();
		return true;
	}
	
	public List<Project> getProjectList() {
		User user = getUser();
		return project_repo.findAll(user);
	}
	
	public List<User> getAllAccountsInProject(Authority authority){
		return project_repo.findAll(getProject(), authority);
	}
}
