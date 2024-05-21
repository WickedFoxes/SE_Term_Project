package main.domain;

import main.domain.enumeration.Authority;

public class Tester extends User{

	public Tester(String id, String password) {
		super(id, password);
	}
	
	@Ovrride
	public Authority getAuthority() {
		return Authority.TESTER;
	}
		
}
