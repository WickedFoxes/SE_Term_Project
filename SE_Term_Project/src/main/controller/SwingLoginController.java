package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.model.LoginModel;
import main.view.SwingLoginView;
import main.view.SwingMainView;

public class SwingLoginController extends SwingController{
	private SwingLoginView view;
	private LoginModel model;
	
	public SwingLoginController(SwingLoginView view, LoginModel model) {
		this.view = view;
		this.view.setLoginListener(new LoginButtonListener());
		this.model = model;
	}
	
	private class LoginButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = view.getID();
			String pw = view.getPW();
			Boolean success = model.tryLogin(id, pw);
			if(success) view.requestChangeView("ProjectListView");
			else view.showMessagePopup("Login Error", "아이디, 또는 비밀번호를 잘못 입력했습니다.", JOptionPane.ERROR_MESSAGE);
		}
	}
}
