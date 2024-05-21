package main.domain;

import main.domain.enumeration.Authority;

public abstract class User {
	private int id;
	private String accountID;
	private String password;
	
	public User(int id, String accountID, String password) {
		this.id = id;
		this.setAccountID(accountID);
		this.setPassword(password);
	}
	public int getId() {
		return id;
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
	
	public abstract Authority getAuthority();
}
