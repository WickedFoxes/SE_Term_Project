package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.model.LoginModel;
import main.view.LogoutableView;

public class SwingLogoutController extends SwingController {
	private LogoutableView view;
	private LoginModel model;
	
	public SwingLogoutController(LogoutableView view, LoginModel model) {
		this.view = view;
		this.view.setLogoutListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		this.model = model;
	}

	private void logout() {
		view.requestChangeView("LoginView");
		model.logout();
		model.notifyObservers();
	}
}
