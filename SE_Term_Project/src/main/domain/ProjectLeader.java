package main.domain;

import main.domain.enumeration.Authority;

public class ProjectLeader extends User{
	
	public ProjectLeader(String accountID, String password) {
		super(accountID, password);
	}
	public ProjectLeader(int id, String accountID, String password) {
		super(id, accountID, password);
	}

	@Override
	public Authority getAuthority() {
		return Authority.PL;
	}

}
