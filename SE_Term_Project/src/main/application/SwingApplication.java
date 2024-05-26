package main.application;

import javax.swing.SwingUtilities;

import main.controller.SwingAccountCreationController;
import main.controller.SwingIssueCreationController;
import main.controller.SwingIssueFilterController;
import main.controller.SwingIssueListController;
import main.controller.SwingLoginController;
import main.controller.SwingLogoutController;
import main.controller.SwingProjectCreationController;
import main.controller.SwingProjectListController;
import main.controller.SwingReturnController;
import main.domain.Admin;
import main.model.AccountModel;
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
import main.view.SwingMainView;

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
	//CommentModel commentModel;
	
	SwingLoginController loginController;
	SwingLogoutController logoutController_projectList, logoutController_issueList;
	SwingReturnController returnController_accountCreation, returnController_projectCreation, returnController_issueList, returnController_issueCreation, returnController_issueDetail;
	SwingProjectListController projectListController;
	SwingAccountCreationController accountCreationController;
	SwingProjectCreationController projectCreationController;
	SwingIssueListController issueListController;
	SwingIssueCreationController issueCreationController;
	SwingIssueFilterController issueFilterController;
	
	public void run() {
		//View
		SwingMainView view = new SwingMainView();
		
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
		//CommentModel commentModel = new CommentModel(systemData, commentRepo);
		
		//Controller
		loginController = new SwingLoginController(view.getLoginView(), loginModel);
		
		projectListController = new SwingProjectListController(view.getProjectListView(), projectListModel);
		logoutController_projectList = new SwingLogoutController((LogoutableView)view.getProjectListView(), loginModel);
		
		accountCreationController = new SwingAccountCreationController(view.getAccountCreationView(), accountModel);
		returnController_accountCreation = new SwingReturnController((ReturnableView)view.getAccountCreationView(), accountModel);
		
		projectCreationController = new SwingProjectCreationController(view.getProjectCreationView(), accountModel, projectListModel);
		returnController_projectCreation = new SwingReturnController((ReturnableView)view.getProjectCreationView(), projectListModel);
		
		issueListController = new SwingIssueListController(view.getIssueListView(), issueListModel);
		logoutController_issueList = new SwingLogoutController((LogoutableView)view.getIssueListView(), loginModel);
		returnController_issueList = new SwingReturnController((ReturnableView)view.getIssueListView(), issueListModel);
		
		issueCreationController = new SwingIssueCreationController(view.getIssueCreationView(), issueListModel);
		returnController_issueList = new SwingReturnController((ReturnableView)view.getIssueCreationView(), issueListModel);
		
		issueFilterController = new SwingIssueFilterController(view.getIssueFilterView(), projectListModel, issueListModel);
		
		
		SwingUtilities.invokeLater(() -> {
			view.setVisible(true);
        });
	}
}
