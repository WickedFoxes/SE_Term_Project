package main.repository;

import main.domain.SystemManager;
import main.domain.User;

public class LoginController {
	private SystemManager systemManager;
	private AccountRepo repo;
	
	public LoginController(SystemManager s, AccountRepo r) {
		this.systemManager = s;
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
