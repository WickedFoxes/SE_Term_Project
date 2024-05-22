package main.model;

import main.domain.SystemManager;

public abstract class Model {
	protected SystemManager systemManager;
	
	public Model(SystemManager s) {
		this.systemManager = s;
	}
}
