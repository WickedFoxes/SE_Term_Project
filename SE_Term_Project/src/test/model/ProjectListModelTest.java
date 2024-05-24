package test.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.domain.*;
import main.domain.enumeration.Authority;
import main.model.*;
import main.repository.MysqlAccountRepo;
import main.repository.MysqlProjectRepo;
import test.repository.RepoTest;

public class ProjectListModelTest {
	RepoTest testrepo = new RepoTest();
	MysqlAccountRepo account_repo = new MysqlAccountRepo();
	MysqlProjectRepo project_repo = new MysqlProjectRepo();
	String crateSQL = "create_db.sql";
	
	SystemManager sysmanager = new SystemManager(); 
	LoginModel login_model = new LoginModel(sysmanager, account_repo);
	AccountModel account_model = new AccountModel(sysmanager, account_repo);
	ProjectListModel projectList_model = new ProjectListModel(sysmanager, project_repo);
	
	Admin admin = new Admin("admin", "admin");
	ProjectLeader pl1 = new ProjectLeader("pl1", "pl1");
	ProjectLeader pl2 = new ProjectLeader("pl2", "pl2");
	Tester tester1 = new Tester("tester1", "tester1");
	Tester tester2 = new Tester("tester2", "tester2");
	Tester tester3 = new Tester("tester3", "tester3");
	Dev dev1 = new Dev("dev1", "dev1");
	Dev dev2 = new Dev("dev2", "dev2");
	Dev dev3 = new Dev("dev3", "dev3");
	Project project1 = new Project("proejct1");
	Project project2 = new Project("proejct2");
	
    @BeforeEach
    void beforeEach() {
    	// 매우중요!!!! 테스트할 때 사용하는 repo는 test.sqlite3로 지정해야 함!!!!!!
    	testrepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	account_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	project_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	testrepo.clearDB();
    	testrepo.createDB(crateSQL);
    	////////////////////////////////////////////////////////////////////
    	
    	// 계정 추가
		account_repo.add(admin);
		account_repo.add(pl1);
		account_repo.add(pl2);
		account_repo.add(tester1);
		account_repo.add(tester2);
		account_repo.add(tester3);
		account_repo.add(dev1);
		account_repo.add(dev2);
		account_repo.add(dev3);
    }
    
    @Test
    void createProject() {
    	login_model.tryLogin("admin", "admin");
    	List<User> pls = account_model.getAccounts(Authority.PL);
    	List<User> devs = account_model.getAccounts(Authority.DEV);
    	List<User> testers = account_model.getAccounts(Authority.TESTER);
    	
    	ProjectLeader select_pl1 = (ProjectLeader)pls.get(0);
    	ProjectLeader select_pl2 = (ProjectLeader)pls.get(1);
    	List<Dev> select_dev = new ArrayList<Dev>();
    	select_dev.add((Dev)devs.get(0));
    	select_dev.add((Dev)devs.get(1));
    	List<Tester> select_tester = new ArrayList<Tester>();
    	select_tester.add((Tester)testers.get(0));
    	select_tester.add((Tester)testers.get(1));
    	
    	projectList_model.createProject("project1", select_pl1, select_dev, select_tester);
    	projectList_model.createProject("project2", select_pl2, select_dev, select_tester);
    	
    	int repo_test = project_repo.findAll(pls.get(0)).size();
    	
    	Assertions.assertEquals(1, repo_test);
    }
    
    @Test
    void getProjectList() {
    	login_model.tryLogin("admin", "admin");
    	List<User> pls = account_model.getAccounts(Authority.PL);
    	List<User> devs = account_model.getAccounts(Authority.DEV);
    	List<User> testers = account_model.getAccounts(Authority.TESTER);
    	
    	ProjectLeader select_pl1 = (ProjectLeader)pls.get(0);
    	ProjectLeader select_pl2 = (ProjectLeader)pls.get(1);
    	List<Dev> select_dev = new ArrayList<Dev>();
    	select_dev.add((Dev)devs.get(0));
    	select_dev.add((Dev)devs.get(1));
    	List<Tester> select_tester = new ArrayList<Tester>();
    	select_tester.add((Tester)testers.get(0));
    	select_tester.add((Tester)testers.get(1));
    	
    	projectList_model.createProject("project1", select_pl1, select_dev, select_tester);
    	projectList_model.createProject("project2", select_pl2, select_dev, select_tester);
    	
    	login_model.logout();
    	login_model.tryLogin("dev1", "dev1");
    	int dev_test = projectList_model.getProjectList().size();
    	Assertions.assertEquals(2, dev_test);
    }
}
