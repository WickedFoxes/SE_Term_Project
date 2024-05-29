package main.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import main.controller.SwingAccountCreationController;
import main.controller.SwingCommentController;
import main.controller.SwingIssueCreationController;
import main.controller.SwingIssueDetailController;
import main.controller.SwingIssueFilterController;
import main.controller.SwingIssueListController;
import main.controller.SwingLoginController;
import main.controller.SwingLogoutController;
import main.controller.SwingProjectCreationController;
import main.controller.SwingProjectListController;
import main.controller.SwingReturnController;
import main.domain.Admin;
import main.model.AccountModel;
import main.model.CommentModel;
import main.model.IssueListModel;
import main.model.IssueModel;
import main.model.LoginModel;
import main.model.ProjectListModel;
import main.model.SystemManager;
import main.repository.AccountRepo;
import main.repository.ProjectRepo;
import main.repository.IssueRepo;
import main.repository.CommentRepo;
import main.repository.MysqlAccountRepo;
import main.repository.MysqlCommentRepo;
import main.repository.MysqlIssueRepo;
import main.repository.MysqlProjectRepo;
import main.view.LogoutableView;
import main.view.ReturnableView;
import main.view.SwingAccountCreationView;
import main.view.SwingMainFrame;
import test.repository.RepoTest;

public class SwingApplication implements Application{
	SystemManager systemData;
	AccountRepo accountRepo;
	ProjectRepo projectRepo;
	IssueRepo issueRepo;
	CommentRepo commentRepo;
	
	LoginModel loginModel;
	AccountModel accountModel;
	ProjectListModel projectListModel;
	IssueListModel issueListModel;
	IssueModel issueModel;
	CommentModel commentModel;
	
	SwingLoginController loginController;
	SwingLogoutController logoutController_projectList, logoutController_issueList;
	SwingReturnController returnController_accountCreation, returnController_projectCreation, returnController_issueList, returnController_issueCreation, returnController_issueDetail;
	SwingProjectListController projectListController;
	SwingAccountCreationController accountCreationController;
	SwingProjectCreationController projectCreationController;
	SwingIssueListController issueListController;
	SwingIssueCreationController issueCreationController;
	SwingIssueFilterController issueFilterController;
	SwingIssueDetailController issueDetailController;
	SwingCommentController commentController;
	
	public void run() {
		SwingUtilities.invokeLater(() -> {
			//View
			SwingMainFrame mainFrame = new SwingMainFrame();
			
			//Repo
			systemData = new SystemManager();
			accountRepo = new MysqlAccountRepo();
			projectRepo = new MysqlProjectRepo();
			issueRepo = new MysqlIssueRepo();
			commentRepo = new MysqlCommentRepo();
			
			//Model
			loginModel = new LoginModel(systemData, accountRepo);
			accountModel = new AccountModel(systemData, accountRepo);
			projectListModel = new ProjectListModel(systemData, projectRepo, accountRepo);
			issueListModel = new IssueListModel(systemData, issueRepo);
			issueModel = new IssueModel(systemData, issueRepo);
			CommentModel commentModel = new CommentModel(systemData, commentRepo);
			
			//Controller
			loginController = new SwingLoginController(mainFrame.getLoginView(), loginModel);
			
			projectListController = new SwingProjectListController(mainFrame.getProjectListView(), projectListModel);
			logoutController_projectList = new SwingLogoutController((LogoutableView)mainFrame.getProjectListView(), loginModel);
			
			accountCreationController = new SwingAccountCreationController(mainFrame.getAccountCreationView(), accountModel);
			returnController_accountCreation = new SwingReturnController((ReturnableView)mainFrame.getAccountCreationView(), accountModel);
			
			projectCreationController = new SwingProjectCreationController(mainFrame.getProjectCreationView(), accountModel, projectListModel);
			returnController_projectCreation = new SwingReturnController((ReturnableView)mainFrame.getProjectCreationView(), projectListModel);
			
			issueListController = new SwingIssueListController(mainFrame.getIssueListView(), issueListModel);
			logoutController_issueList = new SwingLogoutController((LogoutableView)mainFrame.getIssueListView(), loginModel);
			returnController_issueList = new SwingReturnController((ReturnableView)mainFrame.getIssueListView(), issueListModel);
			
			issueCreationController = new SwingIssueCreationController(mainFrame.getIssueCreationView(), issueListModel);
			returnController_issueList = new SwingReturnController((ReturnableView)mainFrame.getIssueCreationView(), issueListModel);
			
			issueFilterController = new SwingIssueFilterController(mainFrame.getIssueFilterView(), projectListModel, issueListModel);
			
			issueDetailController = new SwingIssueDetailController(mainFrame.getIssueDetailView(), projectListModel, issueModel);
			returnController_issueDetail = new SwingReturnController((ReturnableView)mainFrame.getIssueDetailView(), issueModel);
			commentController = new SwingCommentController(mainFrame.getIssueDetailView(), commentModel);

			mainFrame.setVisible(true);
			
			//For Debug
			RepoTest repoTest = new RepoTest();
			JButton button = new JButton("initDB");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					repoTest.clearDB();
					repoTest.createDB("create_db.sql");
					repoTest.initUsers(accountRepo, projectRepo);
				}				
			});
			button.setBounds(390, 180, 80, 20);
			mainFrame.getLoginView().add(button);
        });
	}
}
