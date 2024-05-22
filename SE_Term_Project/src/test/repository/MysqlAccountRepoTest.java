package test.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.domain.*;
import main.domain.enumeration.Authority;
import main.repository.MysqlAccountRepo;

class MysqlAccountRepoTest {
	RepoTest testrepo = new RepoTest();
	MysqlAccountRepo account_repo = new MysqlAccountRepo();
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
	
    @BeforeEach
    void beforeEach() {
    	testrepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	account_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	testrepo.clearDB();
    	testrepo.createDB(crateSQL);
    }
	
	@AfterEach
	public void afterEach() {
	}
	
	@Test
	void add() {
		account_repo.add(admin);
		account_repo.add(pl1);
		account_repo.add(pl2);
		account_repo.add(tester1);
		account_repo.add(tester2);
		account_repo.add(tester3);
		account_repo.add(dev1);
		account_repo.add(dev2);
		account_repo.add(dev3);
		
		int admin_len = account_repo.findAll(Authority.ADMIN).size();
		int pl_len = account_repo.findAll(Authority.PL).size();
		int tester_len = account_repo.findAll(Authority.TESTER).size();
		int dev_len = account_repo.findAll(Authority.DEV).size();
		Assertions.assertEquals(9, admin_len+pl_len+tester_len+dev_len);
	}
	
	@Test
	void find() {
		account_repo.add(admin);
		User user = account_repo.find("admin");
		Assertions.assertEquals("admin", user.getAccountID());
	}
	
	@Test
	void contains() {
		account_repo.add(admin);
		account_repo.add(pl1);
		account_repo.add(pl2);
		account_repo.add(tester1);
		account_repo.add(tester2);
		account_repo.add(tester3);
		Assertions.assertEquals(true, account_repo.contains("tester1"));
	}
}
