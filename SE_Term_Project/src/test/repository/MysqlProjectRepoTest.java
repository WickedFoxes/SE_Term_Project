package test.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.domain.*;
import main.domain.enumeration.Authority;
import main.repository.MysqlAccountRepo;
import main.repository.MysqlProjectRepo;

class MysqlProjectRepoTest {
	RepoTest testrepo = new RepoTest();
	MysqlAccountRepo account_repo = new MysqlAccountRepo();
	MysqlProjectRepo project_repo = new MysqlProjectRepo();
	String crateSQL = "create_db.sql";
	
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
    	testrepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	account_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	project_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	testrepo.clearDB();
    	testrepo.createDB(crateSQL);
    	
    	// id 작성(실제로는 db에 입력된 순서로 id가 저장됨)
    	admin.setId(1);
    	pl1.setId(2);
    	pl2.setId(3);
    	tester1.setId(4);
    	tester2.setId(5);
    	tester3.setId(6);
    	dev1.setId(7);
    	dev2.setId(8);
    	dev3.setId(9);
    	
    	project1.setId(1);
    	project2.setId(2);
    	
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
		
		// 프로젝트 추가
		project_repo.add(project1);
		project_repo.add(project2);
		
		project_repo.add(project1, pl1);
		project_repo.add(project1, dev1);
		project_repo.add(project1, tester1);
		
		project_repo.add(project2, pl1);
    }
	
	@AfterEach
	public void afterEach() {
	}

	@Test
	void findAll_User() {
		int dev_len = project_repo.findAll(project1, Authority.DEV).size();
		int pl_len = project_repo.findAll(project1, Authority.PL).size();
		int tester_len = project_repo.findAll(project1, Authority.TESTER).size();
		Assertions.assertEquals(3, pl_len+tester_len+dev_len);
	}

	@Test
	void findAll_Project() {
		int project_len = project_repo.findAll(pl1).size();
		Assertions.assertEquals(2, project_len);
	}
	
}
