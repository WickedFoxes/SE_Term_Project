package main.controller;

import main.model.LoginModel;

abstract public class LoginController {
	protected LoginModel model;
	
	public LoginController(LoginModel model) {
		this.model = model;
	}

}
