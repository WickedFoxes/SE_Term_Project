package main.domain;

import main.domain.enumeration.Authority;

public class Admin extends User {
	public Admin(String accountID, String password) {
		super(accountID, password);
	}

	@Override
	public Authority getAuthority() {
		return Authority.ADMIN;
	}
}
