package main.model;

import main.domain.SystemManager;
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
		systemManager.setUser(user);
		return true;
	}
	
	public void logout() {
		systemManager.setUser(null);
	}
}
