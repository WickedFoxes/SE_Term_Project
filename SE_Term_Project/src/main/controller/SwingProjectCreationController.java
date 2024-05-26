package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.domain.Dev;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.model.AccountModel;
import main.model.Observer;
import main.model.ProjectListModel;
import main.view.SwingProjectCreationView;

public class SwingProjectCreationController extends SwingController {
	private SwingProjectCreationView view;
	private AccountModel accountModel;
	private ProjectListModel projectListModel;
	
	public SwingProjectCreationController(SwingProjectCreationView view, AccountModel accountModel, ProjectListModel projectListModel) {
		this.accountModel = accountModel;
		this.projectListModel = projectListModel;
		this.view = view;
		this.view.setCreateListener(new CreateButtonListener());
		setObserver();
	}
	
	private void setObserver() {
		accountModel.subscribe(new Observer() {
			public void update() {
				setListData();
			}
		});
	}
	
	private void setListData() {
		if(accountModel.getUser() == null) return;
 		
		List<ProjectLeader> pls = new ArrayList<ProjectLeader>(); 
		List<Dev> devs = new ArrayList<Dev>();
		List<Tester> testers = new ArrayList<Tester>(); 

		for(User pl : accountModel.getAllAccounts(Authority.PL)) pls.add((ProjectLeader)pl);
		for(User dev : accountModel.getAllAccounts(Authority.DEV)) devs.add((Dev)dev);
		for(User tester : accountModel.getAllAccounts(Authority.TESTER)) testers.add((Tester)tester);
		
		view.setPLList(pls);
		view.setDevList(devs);
		view.setTesterList(testers);
	}
	
	private class CreateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = view.getName();
			ProjectLeader pl = view.getPL();
			List<Dev> devs = view.getDevs();
			List<Tester> testers = view.getTesters();
			
			boolean success = projectListModel.tryCreateProject(name, pl, devs, testers);
			if(success) {
				view.showMessagePopup("Project Creation success", "프로젝트 생성이 완료되었습니다.", JOptionPane.INFORMATION_MESSAGE);
				view.requestChangeView("ProjectListView");
			}
			else {
				view.showMessagePopup("Project Creation Error", "입력 정보를 확인해주세요.", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
