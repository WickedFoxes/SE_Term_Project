package main.application;

import main.controller.SwingLoginController;
import main.controller.SwingLogoutController;
import main.controller.SwingProjectListController;
import main.domain.Admin;
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
import main.view.SwingAccountCreationView;
import main.view.SwingMainView;

public class SwingApplication implements Application{
	public void run() {
		SwingMainView view = new SwingMainView();
		
//		SystemManager systemData = new SystemManager();
//		AccountRepo accountRepo = new MysqlAccountRepo();
//		ProjectRepo projectRepo = new MysqlProjectRepo();
//		IssueRepo issueRepo = new MysqlIssueRepo();
//		CommentRepo commentRepo = new MysqlCommentRepo();
//		
//		LoginModel loginModel = new LoginModel(systemData, accountRepo);
//		ProjectListModel projectListModel = new ProjectListModel(systemData, projectRepo, accountRepo);
//		IssueListModel issueListModel = new IssueListModel(systemData, issueRepo);
//		IssueModel issueModel = new IssueModel(systemData, issueRepo);
//		//CommentModel commentModel = new CommentModel(systemData, commentRepo);
//		
//		SwingLoginController loginController = new SwingLoginController(view.getLoginView(), loginModel);
//		SwingLogoutController logoutController1 = new SwingLogoutController((LogoutableView)view.getProjectListView(), loginModel);
//		SwingProjectListController projectListController = new SwingProjectListController(view.getProjectListView(), projectListModel);
	}
}
