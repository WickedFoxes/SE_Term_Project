package main.domain;

import main.domain.enumeration.Authority;

public class Tester extends User{
	public Tester(String accountID, String password) {
		super(accountID, password);
	}

	@Override
	public Authority getAuthority() {
		return Authority.TESTER;
	}
		
}
