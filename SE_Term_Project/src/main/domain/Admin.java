package main.domain;

import main.domain.enumeration.Authority;

public class Admin extends User {
	public Admin(String accountID, String password) {
		super(accountID, password);
	}
	public Admin(int id, String accountID, String password) {
		super(id, accountID, password);
	}

	@Override
	public Authority getAuthority() {
		return Authority.ADMIN;
	}
}
