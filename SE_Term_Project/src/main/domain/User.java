package main.domain;

import main.domain.enumeration.Authority;

public abstract class User {
	private int id;
	private String accountID;
	private String password;
	
	public User(String accountID, String password) {
		this.accountID = accountID;
		this.password = password;
	}
	
	public User(int id, String accountID, String password) {
		this.id = id;
		this.accountID = accountID;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccountID() {
		return accountID;
	}
	
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return accountID;
	}
	
	public abstract Authority getAuthority();
}
