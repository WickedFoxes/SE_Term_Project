package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.domain.*;
import main.domain.enumeration.*;
import main.model.*;
import main.repository.MysqlAccountRepo;
import test.repository.RepoTest;

class AccountModelTest {
	RepoTest testrepo = new RepoTest();
	MysqlAccountRepo account_repo = new MysqlAccountRepo();
	String crateSQL = "create_db.sql";
	
	SystemManager sysmanager = new SystemManager(); 
	LoginModel login_model = new LoginModel(sysmanager, account_repo);
	AccountModel account_model = new AccountModel(sysmanager, account_repo);
	
	Admin admin = new Admin("admin", "admin");
	
    @BeforeEach
    void beforeEach() {
    	// 매우중요!!!! 테스트할 때 사용하는 repo는 test.sqlite3로 지정해야 함!!!!!!
    	testrepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	account_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	testrepo.clearDB();
    	testrepo.createDB(crateSQL);
    	////////////////////////////////////////////////////////////////////
    	
    	// admin 계정 추가
    	account_repo.add(admin);
    }
    
    @Test
    void signup() {
    	login_model.tryLogin("admin", "admin");
    	account_model.trySignup("pl1", "pl1", Authority.PL);
    	account_model.trySignup("dev1", "dev1", Authority.DEV);
    	account_model.trySignup("tester1", "tester1", Authority.TESTER);
    	
    	login_model.logout();
    	login_model.tryLogin("dev1", "dev1");
    	Assertions.assertEquals("dev1", sysmanager.getUser().getAccountID());
    }
}
