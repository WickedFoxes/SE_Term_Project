package main.controller;

import main.model.AccountModel;
import main.model.LoginModel;

public abstract class AccountController {
	protected AccountModel model;
	public AccountController(AccountModel model) {
		this.model = model;
	}
}
