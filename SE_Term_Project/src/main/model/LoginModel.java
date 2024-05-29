package main.model;

import main.domain.User;
import main.repository.AccountRepo;

public class LoginModel extends Model {
	private AccountRepo repo;
	
	public LoginModel(SystemManager s, AccountRepo r) {
		super(s);
		this.repo = r;
	}
	
	public boolean tryLogin(String id, String pw) {
		if(!repo.contains(id)) return false;
		User user = repo.find(id);
		if(!user.getPassword().equals(pw)) return false;
		setUser(user);
		setProject(null);
		setIssue(null);
		return true;
	}
	
	public void logout() {
		setUser(null);
		setProject(null);
		setIssue(null);
	}
}
