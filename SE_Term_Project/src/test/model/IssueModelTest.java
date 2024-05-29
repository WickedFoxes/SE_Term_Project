package test.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.domain.Admin;
import main.domain.Dev;
import main.domain.Issue;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;
import main.model.AccountModel;
import main.model.IssueListModel;
import main.model.IssueModel;
import main.model.LoginModel;
import main.model.ProjectListModel;
import main.model.SystemManager;
import main.repository.MysqlAccountRepo;
import main.repository.MysqlIssueRepo;
import main.repository.MysqlProjectRepo;
import test.repository.RepoTest;

public class IssueModelTest {
	List<User> pls, devs, testers;
	List<Project> projects;
		
	RepoTest testRepo = new RepoTest();
	
	String crateSQL = "create_db.sql";
	MysqlAccountRepo accountRepo = new MysqlAccountRepo();
	MysqlProjectRepo projectRepo = new MysqlProjectRepo();
	MysqlIssueRepo issueRepo = new MysqlIssueRepo();
	
	SystemManager manager = new SystemManager(); 
	LoginModel loginModel = new LoginModel(manager, accountRepo);
	AccountModel accountModel = new AccountModel(manager, accountRepo);
	ProjectListModel projectListModel = new ProjectListModel(manager, projectRepo, accountRepo);
	IssueListModel issueListModel = new IssueListModel(manager, issueRepo); 
	IssueModel issueModel = new IssueModel(manager, issueRepo, projectRepo);
	
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
    	pls = accountModel.getAllAccounts(Authority.PL);
    	devs = accountModel.getAllAccounts(Authority.DEV);
    	testers = accountModel.getAllAccounts(Authority.TESTER);
    	
    	ProjectLeader select_pl1 = (ProjectLeader)pls.get(0);
    	ProjectLeader select_pl2 = (ProjectLeader)pls.get(1);
    	List<Dev> select_dev = new ArrayList<Dev>();
    	select_dev.add((Dev)devs.get(0));
    	select_dev.add((Dev)devs.get(1));
    	select_dev.add((Dev)devs.get(2));
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
    void setState_PL_NEW() {
    	// account
    	List<User> devs = accountModel.getAllAccounts(Authority.DEV);
    	
    	// pl1 행동
    	loginModel.tryLogin("pl1", "pl1");
    	List<Project> project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	List<Issue> issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	System.out.println(issue_contents.get(0));
    	// dev1 assignee로 지정
    	issueModel.modify(issue_contents.get(0), null, State.ASSIGNED, (Dev)devs.get(0));
    	
    	// dev1 행동
    	loginModel.tryLogin("dev1", "dev1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	Assertions.assertEquals(1, issue_contents.size());
    }
    
    @Test
    void setState_DEV_ASSIGNED() {
    	// account
    	List<User> devs = accountModel.getAllAccounts(Authority.DEV);
    	
    	// pl1 행동
    	loginModel.tryLogin("pl1", "pl1");
    	List<Project> project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	List<Issue> issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	System.out.println(issue_contents.get(0));
    	// dev1 assignee로 지정
    	issueModel.modify(issue_contents.get(0), null, State.ASSIGNED, (Dev)devs.get(0));
    	
    	// dev1 행동
    	loginModel.tryLogin("dev1", "dev1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	// assigned -> fixed로 변경
    	issueModel.modify(issue_contents.get(0), null, State.FIXED, null);
    	issue_contents = issueListModel.getIssueList();
    	
    	Assertions.assertEquals(State.FIXED, issue_contents.get(0).getState());
    }
    
    @Test
    void setState_TESTER_FIXED() {
    	// account
    	List<User> devs = accountModel.getAllAccounts(Authority.DEV);
    	
    	// pl1 행동
    	loginModel.tryLogin("pl1", "pl1");
    	List<Project> project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	List<Issue> issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	System.out.println(issue_contents.get(0));
    	// dev1 assignee로 지정
    	issueModel.modify(issue_contents.get(0), null, State.ASSIGNED, (Dev)devs.get(0));
    	
    	// dev1 행동
    	loginModel.tryLogin("dev1", "dev1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	// assigned -> fixed로 변경
    	issueModel.modify(issue_contents.get(0), null, State.FIXED, null);
    	issue_contents = issueListModel.getIssueList();

    	// fixed -> resolved로 변경
    	loginModel.tryLogin("tester1", "tester1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	issueModel.modify(issue_contents.get(0), null, State.RESOLVED, null);
    	issue_contents = issueListModel.getIssueList();
    	
    	Assertions.assertEquals(State.RESOLVED, issue_contents.get(0).getState());
    }
    
    @Test
    void setState_PL_RESOLVED() {
    	// account
    	List<User> devs = accountModel.getAllAccounts(Authority.DEV);
    	
    	// pl1 행동
    	loginModel.tryLogin("pl1", "pl1");
    	List<Project> project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	List<Issue> issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	System.out.println(issue_contents.get(0));
    	// dev1 assignee로 지정
    	issueModel.modify(issue_contents.get(0), null, State.ASSIGNED, (Dev)devs.get(0));
    	
    	// dev1 행동
    	loginModel.tryLogin("dev1", "dev1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	// assigned -> fixed로 변경
    	issueModel.modify(issue_contents.get(0), null, State.FIXED, null);
    	issue_contents = issueListModel.getIssueList();

    	// fixed -> resolved로 변경
    	loginModel.tryLogin("tester1", "tester1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	issueModel.modify(issue_contents.get(0), null, State.RESOLVED, null);
    	issue_contents = issueListModel.getIssueList();
    	
    	// resolved -> closed로 변경
    	loginModel.tryLogin("pl1", "pl1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	issueModel.modify(issue_contents.get(0), null, State.CLOSED, null);
    	issue_contents = issueListModel.getIssueList();
    	
    	Assertions.assertEquals(State.CLOSED, issue_contents.get(0).getState());
    }
    
    @Test
    void getRecommendAssignee() {
    	// account
    	List<User> devs = accountModel.getAllAccounts(Authority.DEV);
    	
    	// pl1 행동
    	loginModel.tryLogin("pl1", "pl1");
    	List<Project> project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	List<Issue> issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	System.out.println(issue_contents.get(0));
    	// dev1 assignee로 지정
    	issueModel.modify(issue_contents.get(0), null, State.ASSIGNED, (Dev)devs.get(0));
    	
    	// dev1 행동
    	loginModel.tryLogin("dev1", "dev1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	// assigned -> fixed로 변경
    	issueModel.modify(issue_contents.get(0), null, State.FIXED, null);
    	issue_contents = issueListModel.getIssueList();

    	// fixed -> resolved로 변경
    	loginModel.tryLogin("tester1", "tester1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(0));
    	
    	issueModel.modify(issue_contents.get(0), null, State.RESOLVED, null);
    	issue_contents = issueListModel.getIssueList();
    	
    	
    	// isssue2 생성
    	issueListModel.tryCreateIssue("issue2", "issue description2", Priority.MINOR);
    	
    	loginModel.tryLogin("pl1", "pl1");
    	project_contents = projectListModel.getProjectList();
    	issueModel.setProject(project_contents.get(0));
    	issue_contents = issueListModel.getIssueList();
    	issueModel.setIssue(issue_contents.get(1));
    	// dev2 assignee로 지정
    	issueModel.modify(issue_contents.get(0), null, State.ASSIGNED, (Dev)devs.get(1));
    	
    	List<User> sorted_devs = issueModel.getRecommedAssignee();
    	for(User d : sorted_devs) {
    		System.out.println(d.getAccountID());
    	}
    	Assertions.assertEquals("dev2", sorted_devs.get(2).getAccountID());
    }
}
