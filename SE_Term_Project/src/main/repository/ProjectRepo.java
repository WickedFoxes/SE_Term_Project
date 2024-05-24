package main.repository;

import java.util.List;

import main.domain.Project;
import main.domain.User;
import main.domain.enumeration.Authority;

public interface ProjectRepo {
	public Project add(Project project);
	public void add(Project project, User user);
	public boolean contains(Project project, User user);
	public List<User> findAll(Project project, Authority authority);
	public List<Project> findAll(User user);
}
