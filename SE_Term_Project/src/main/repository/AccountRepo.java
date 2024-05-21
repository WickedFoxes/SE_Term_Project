package main.repository;

import main.domain.Dev;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;

public interface AccountRepo {
	public void add(ProjectLeader pl);
	public void add(Dev dev);
	public void add(Tester tester);
	
	public boolean contains(String accountID);
	
	public User find(String accountID);
	public User[] findAll(Authority authority);
}
