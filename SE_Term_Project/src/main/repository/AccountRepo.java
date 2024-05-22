package main.repository;

import java.util.List;

import main.domain.User;
import main.domain.enumeration.Authority;

public interface AccountRepo {
	public void add(User user);
	
	public boolean contains(String accountID);
	
	public User find(String accountID);
	public List<User> findAll(Authority authority);
}
