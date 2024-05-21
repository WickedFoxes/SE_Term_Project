package main.domain;

import main.domain.enumeration.Authority;

public class Dev extends User{

	public Dev(int id, String accountID, String password) {
		super(id, accountID, password);
	}

	@Override
	public Authority getAuthority() {
		return Authority.DEV;
	}
}
