package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.view.TestView;

public class TestController2 implements ActionListener {
	private LoginController loginController;
	private TestView view;
	
	public TestController2(LoginController l, TestView view) {
		this.loginController = l;
		this.view = view;
		this.view.setLogoutListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		loginController.logout();
		//view something
	}
}
