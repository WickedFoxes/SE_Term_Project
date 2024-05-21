package main.domain;

import main.domain.enumeration.Authority;

public class Tester extends User{

	public Tester(int id, String accountID, String password) {
		super(id, accountID, password);
	}

	@Override
	public Authority getAuthority() {
		return Authority.TESTER;
	}
		
}
