package main.domain;

import main.domain.enumeration.Authority;

public class Account {
	private String id;
	private String password;
	private Authority authority;
	public Account(String id, String password, Authority authority) {
		this.setId(id);
		this.setPassword(password);
		this.setAuthority(authority);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
}
