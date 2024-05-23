package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.model.LoginModel;
import main.view.SwingLoginView;

public class SwingLoginController implements ActionListener {
	private LoginModel model;
	private SwingLoginView view;
	
	public SwingLoginController(LoginModel model, SwingLoginView view) {
		this.model = model;
		this.view = view;
		this.view.setLoginListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = view.getID();
		String pw = view.getPW();
		Boolean success = model.tryLogin(id, pw);
		if(success); //view something
		else; //view something
	}
}
