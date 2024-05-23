//package main.controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import main.view.TestView;
//
//public class TestController implements ActionListener {
//	private LoginController loginController;
//	private TestView view;
//	
//	public TestController(LoginController l, TestView view) {
//		this.loginController = l;
//		this.view = view;
//		this.view.setLoginListener(this);
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		String id = view.getID();
//		String pw = view.getPW();
//		Boolean success = loginController.tryLogin(id, pw);
//		if(success); //view something
//		else; //view something
//	}
//}
