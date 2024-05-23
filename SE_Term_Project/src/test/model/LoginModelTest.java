package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.domain.*;
import main.model.LoginModel;
import main.repository.MysqlAccountRepo;
import test.repository.RepoTest;

public class LoginModelTest {
	RepoTest testrepo = new RepoTest();
	MysqlAccountRepo account_repo = new MysqlAccountRepo();
	String crateSQL = "create_db.sql";
	
	SystemManager sysmanager = new SystemManager(null); 
	LoginModel login_model = new LoginModel(sysmanager, account_repo);
	
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
    void tryLogin() {
    	login_model.tryLogin("admin", "admin");
    	Assertions.assertEquals("admin", sysmanager.getUser().getAccountID());
    }
    
    @Test
    void logout() {
    	login_model.tryLogin("admin", "admin");
    	login_model.logout();
    	Assertions.assertEquals(null, sysmanager.getUser());
    }
    
}
