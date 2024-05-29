package test.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.domain.Admin;
import main.domain.Dev;
import main.domain.FilterOption;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.Issue;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;
import main.model.AccountModel;
import main.model.IssueListModel;
import main.model.LoginModel;
import main.model.ProjectListModel;
import main.model.SystemManager;
import main.repository.MysqlAccountRepo;
import main.repository.MysqlIssueRepo;
import main.repository.MysqlProjectRepo;
import test.repository.RepoTest;

public class IssueListModelTest {
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
	IssueListModel issueListModel = new IssueListModel(manager, issueRepo, projectRepo); 
	
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
    	List<Tester> select_tester = new ArrayList<Tester>();
    	select_tester.add((Tester)testers.get(0));
    	select_tester.add((Tester)testers.get(1));
    	projectListModel.tryCreateProject("project1", select_pl1, select_dev, select_tester);
    	projectListModel.tryCreateProject("project2", select_pl2, select_dev, select_tester);
    }
    
    @Test
    void createIssue() {
    	loginModel.logout();
    	loginModel.tryLogin("tester1", "tester1");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue1", "This is test for issue1.", Priority.MAJOR));
    }
    
    @Test
    void createIssue_complexVer() {
    	
    	//case 1
    	loginModel.logout();
    	loginModel.tryLogin("tester1", "tester1");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue1", "This is test for issue1.", Priority.MAJOR));
    	
    	Issue issue1 = issueRepo.findAll(projects.get(0), testers.get(0)).get(0);
    	Assertions.assertEquals("Issue1", issue1.getTitle());
    	Assertions.assertEquals("This is test for issue1.", issue1.getDescription());
    	Assertions.assertEquals(Priority.MAJOR, issue1.getPriority());
    	Assertions.assertEquals(State.NEW, issue1.getState());
    	Assertions.assertEquals(testers.get(0).getId(), issue1.getReporter().getId());
    	
    	//case 2
    	loginModel.logout();
    	loginModel.tryLogin("tester2", "tester2");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue2", "This is test for issue2.", Priority.CRITICAL));
    	Issue issue2 = issueRepo.findAll(projects.get(0), testers.get(1)).get(0);
    	
    	Assertions.assertEquals("Issue2", issue2.getTitle());
    	Assertions.assertEquals("This is test for issue2.", issue2.getDescription());
    	Assertions.assertEquals(Priority.CRITICAL, issue2.getPriority());
    	Assertions.assertEquals(State.NEW, issue2.getState());
    	Assertions.assertEquals(testers.get(1).getId(), issue2.getReporter().getId());
    }
    
    @Test
    void getIssueList() {
    	loginModel.logout();
    	loginModel.tryLogin("tester1", "tester1");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue1", "This is test for issue1.", Priority.MAJOR));
    	
    	List<Issue> issueList = issueRepo.findAll(projects.get(0), testers.get(1));
    	Assertions.assertEquals(0, issueList.size());
    }
    
    @Test
    void getIssueList_complexVer() {
    	loginModel.logout();
    	loginModel.tryLogin("tester2", "tester2");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue0", "This is test for issue0.", Priority.MAJOR));
    	
    	loginModel.logout();
    	loginModel.tryLogin("tester1", "tester1");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue1", "This is test for issue1.", Priority.MAJOR));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue2", "This is test for issue2.", Priority.CRITICAL));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue3", "This is test for issue3.", Priority.CRITICAL));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue4", "This is test for issue4.", Priority.MAJOR));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue5", "This is test for issue5.", Priority.MINOR));
    	
    	List<Issue> issueList = issueRepo.findAll(projects.get(0), testers.get(0));
    	Assertions.assertEquals(5, issueList.size());
    }
    
    @Test
    void getIssueListWithFilter() {
    	loginModel.logout();
    	loginModel.tryLogin("tester2", "tester2");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue0", "This is test for issue0.", Priority.MAJOR));

    	loginModel.logout();
    	loginModel.tryLogin("pl1", "pl1");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	
    	FilterOption option;
    	List<Issue> issueList;
    	
    	option = new FilterOption(State.ASSIGNED, null, null);
    	issueList = issueRepo.findAll(projects.get(0), pls.get(0), option);
    	Assertions.assertEquals(0, issueList.size());
    	
    	option = new FilterOption(State.NEW, null, null);
    	issueList = issueRepo.findAll(projects.get(0), pls.get(0), option);
    	Assertions.assertEquals(1, issueList.size());
    	
    	option = new FilterOption(State.NEW, (Tester)testers.get(0), null);
    	issueList = issueRepo.findAll(projects.get(0), pls.get(0), option);
    	Assertions.assertEquals(0, issueList.size());
    	
    	option = new FilterOption(State.NEW, (Tester)testers.get(1), null);
    	issueList = issueRepo.findAll(projects.get(0), pls.get(0), option);
    	Assertions.assertEquals(1, issueList.size());
    }
    
    @Test
    void getIssueListWithFilter_complexVer() {
    	loginModel.logout();
    	loginModel.tryLogin("tester2", "tester2");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue0", "This is test for issue0.", Priority.MAJOR));
    	
    	loginModel.logout();
    	loginModel.tryLogin("tester1", "tester1");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue1", "This is test for issue1.", Priority.MAJOR));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue2", "This is test for issue2.", Priority.CRITICAL));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue3", "This is test for issue3.", Priority.CRITICAL));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue4", "This is test for issue4.", Priority.MAJOR));
    	Assertions.assertTrue(issueListModel.tryCreateIssue("Issue5", "This is test for issue5.", Priority.MINOR));
    	
    	loginModel.logout();
    	loginModel.tryLogin("pl1", "pl1");
    	projects = projectListModel.getProjectList();
    	manager.setProject(projects.get(0));
    	
    	FilterOption option;
    	List<Issue> issueList;
    	
    	option = new FilterOption(State.ASSIGNED, null, null);
    	issueList = issueRepo.findAll(projects.get(0), pls.get(0), option);
    	Assertions.assertEquals(0, issueList.size());
    	
    	option = new FilterOption(State.NEW, null, null);
    	issueList = issueRepo.findAll(projects.get(0), pls.get(0), option);
    	Assertions.assertEquals(6, issueList.size());
    	
    	option = new FilterOption(State.NEW, (Tester)testers.get(0), null);
    	issueList = issueRepo.findAll(projects.get(0), pls.get(0), option);
    	Assertions.assertEquals(5, issueList.size());
    	
    	option = new FilterOption(State.NEW, (Tester)testers.get(1), null);
    	issueList = issueRepo.findAll(projects.get(0), pls.get(0), option);
    	Assertions.assertEquals(1, issueList.size());
    }
}
