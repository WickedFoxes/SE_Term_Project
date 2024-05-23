package main.model;

import main.domain.SystemManager;
import main.domain.User;

public abstract class Model {
	protected SystemManager systemManager;
	
	public Model(SystemManager s) {
		this.systemManager = s;
	}
	
	public User getUser() {
		return systemManager.getUser();
	}
}
