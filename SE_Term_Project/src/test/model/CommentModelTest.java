package test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.domain.Admin;
import main.domain.Comment;
import main.domain.Dev;
import main.domain.Issue;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.model.AccountModel;
import main.model.CommentModel;
import main.model.IssueListModel;
import main.model.IssueModel;
import main.model.LoginModel;
import main.model.ProjectListModel;
import main.model.SystemManager;
import main.repository.MysqlAccountRepo;
import main.repository.MysqlCommentRepo;
import main.repository.MysqlIssueRepo;
import main.repository.MysqlProjectRepo;
import test.repository.RepoTest;

class CommentModelTest {
	List<User> pls, devs, testers;
	List<Project> projects;
		
	RepoTest testRepo = new RepoTest();
	
	String crateSQL = "create_db.sql";
	MysqlAccountRepo accountRepo = new MysqlAccountRepo();
	MysqlProjectRepo projectRepo = new MysqlProjectRepo();
	MysqlIssueRepo issueRepo = new MysqlIssueRepo();
	MysqlCommentRepo commentRepo = new MysqlCommentRepo();
	
	SystemManager manager = new SystemManager(); 
	LoginModel loginModel = new LoginModel(manager, accountRepo);
	AccountModel accountModel = new AccountModel(manager, accountRepo);
	ProjectListModel projectListModel = new ProjectListModel(manager, projectRepo, accountRepo);
	IssueListModel issueListModel = new IssueListModel(manager, issueRepo); 
	IssueModel issueModel = new IssueModel(manager, issueRepo);
	CommentModel commentModel = new CommentModel(manager, commentRepo);
	
	Admin admin = new Admin("admin", "admin");
	ProjectLeader pl1 = new ProjectLeader("pl1", "pl1");
	ProjectLeader pl2 = new ProjectLeader("pl2", "pl2");
	Tester tester1 = new Tester("tester1", "tester1");
	Tester tester2 = new Tester("tester2", "tester2");
	Tester tester3 = new Tester("tester3", "tester3");
	Dev dev1 = new Dev("dev1", "dev1");
	Dev dev2 = new Dev("dev2", "dev2");
	Dev dev3 = new Dev("dev3", "dev3");
	
    @BeforeEach
    void beforeEach() {
    	// 매우중요!!!! 테스트할 때 사용하는 repo는 test.sqlite3로 지정해야 함!!!!!!
    	testRepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	accountRepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	projectRepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	issueRepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	commentRepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	testRepo.clearDB();
    	testRepo.createDB(crateSQL);
    	////////////////////////////////////////////////////////////////////
    	
    	// 계정 추가
    	accountRepo.add(admin);
    	accountRepo.add(pl1);
    	accountRepo.add(pl2);
    	accountRepo.add(tester1);
    	accountRepo.add(tester2);
    	accountRepo.add(tester3);
    	accountRepo.add(dev1);
    	accountRepo.add(dev2);
    	accountRepo.add(dev3);
    	
    	//로그인
    	loginModel.tryLogin("admin", "admin");
    	
    	//프로젝트 생성
    	pls = accountModel.getAccounts(Authority.PL);
    	devs = accountModel.getAccounts(Authority.DEV);
    	testers = accountModel.getAccounts(Authority.TESTER);
    	
    	ProjectLeader select_pl1 = (ProjectLeader)pls.get(0);
    	ProjectLeader select_pl2 = (ProjectLeader)pls.get(1);
    	List<Dev> select_dev = new ArrayList<Dev>();
    	select_dev.add((Dev)devs.get(0));
    	select_dev.add((Dev)devs.get(1));
    	List<Tester> select_tester = new ArrayList<Tester>();
    	select_tester.add((Tester)testers.get(0));
    	select_tester.add((Tester)testers.get(1));
    	projectListModel.tryCreateProject("project1", select_pl1, select_dev, select_tester);
    	projectListModel.tryCreateProject("project2", select_pl2, select_dev, select_tester);
    	
    	//로그인
    	loginModel.tryLogin("tester1", "tester1");
    	List<Project> project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issueListModel.tryCreateIssue("issue1", "issue description1", Priority.MINOR);
    }
    
    @Test
    void getCommentList() {
    	loginModel.tryLogin("tester1", "tester1");
    	// 프로젝트 선택
    	List<Project> project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	
    	// 이슈 선택
    	List<Issue> issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	// 이슈 커맨트 생성
    	Comment input1 = new Comment("comment 1", commentModel.getUser());
    	Comment input2 = new Comment("comment 2", commentModel.getUser());
    	commentModel.createComment(input1);
    	commentModel.createComment(input2);
    	
    	// comment 확인
    	Assertions.assertEquals(2, commentModel.getCommentList().size());
    	
    }
	
}
