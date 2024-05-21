package main.repository;

import main.domain.Project;
import main.domain.User;
import main.domain.enumeration.Authority;

public interface ProjectRepo {
	public void add(Project project);
	public void add(Project project, User user);
	public boolean contains(Project project, User user);
	public User[] find(Project project, Authority authority);
	public Project[] find(User user);
}
