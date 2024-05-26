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
		this.view.setCreateListener(new CreateButtonListener());
	}
	
	private class CreateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = view.getID();
			String pw = view.getPW();
			Authority authority = view.getAuthority();
			
			boolean success = model.trySignup(id, pw, authority);
			if(success) {
				view.showMessagePopup("Signup success", "계정 생성이 완료되었습니다.", JOptionPane.INFORMATION_MESSAGE);
				view.requestChangeView("ProjectListView");
			}
			else {
				view.showMessagePopup("Signup Error", "입력 정보를 확인해주세요.", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
