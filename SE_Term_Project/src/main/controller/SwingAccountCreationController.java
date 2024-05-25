package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.domain.enumeration.Authority;
import main.model.AccountModel;
import main.view.SwingAccountCreationView;

public class SwingAccountCreationController extends SwingController{
	private SwingAccountCreationView view;
	private AccountModel model;
	
	public SwingAccountCreationController(SwingAccountCreationView view, AccountModel model) {
		this.model = model;
		this.view = view;
		this.view.setOKListener(new OKButtonListener());
	}
	
	private class OKButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = view.getID();
			String pw = view.getPW();
			Authority authority = view.getAuthority();
			
			boolean success = model.signup(id, pw, authority);
			if(success) {
				view.showMessagePopup("Signup success", "회원 가입이 완료되었습니다.", JOptionPane.OK_OPTION);
				view.requestChangeView("ProjectListView");
			}
			else {
				view.showMessagePopup("Signup Error", "이미 존재하는 아이디입니다.", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
