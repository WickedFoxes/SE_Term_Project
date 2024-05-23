package main.domain;

import main.domain.enumeration.Authority;

public abstract class User {
	private int id;
	private String accountID;
	private String password;
	
	public User(String accountID, String password) {
		this.setAccountID(accountID);
		this.setPassword(password);
	}

	
	public abstract Authority getAuthority();


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
}
