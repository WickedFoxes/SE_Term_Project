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
	
	public void login(String id, String pw) {
		if(!repo.contains(id)) {
			//popup
			return;
		}
		User user = repo.find(id);
		systemManager.setUser(user);
		//change view
	}
	
	public void logout() {
		systemManager.setUser(null);
		//change view
	}
}
