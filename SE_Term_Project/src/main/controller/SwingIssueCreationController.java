package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.model.IssueListModel;
import main.view.SwingAccountCreationView;
import main.view.SwingIssueCreationView;

public class SwingIssueCreationController extends SwingController{
	private SwingIssueCreationView view;
	private IssueListModel model;
	
	public SwingIssueCreationController(SwingIssueCreationView view, IssueListModel model) {
		this.model = model;
		this.view = view;
		this.view.setCreateListener(new CreateButtonListener());
	}
	
	private class CreateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String title = view.getTitle();
			String description = view.getDescription();
			Priority priority = view.getPriority();
			
			boolean success = model.tryCreateIssue(title, description, priority);
			if(success) {
				view.showMessagePopup("Issue Creation success", "이슈 생성이 완료되었습니다.", JOptionPane.INFORMATION_MESSAGE);
				view.requestChangeView("IssueListView");
				model.notifyObservers();
			}
			else {
				view.showMessagePopup("Issue Creation Error", "입력 정보를 확인해주세요.", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
