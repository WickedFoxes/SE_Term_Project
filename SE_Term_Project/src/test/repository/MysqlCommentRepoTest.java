package test.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.domain.*;
import main.domain.enumeration.*;
import main.repository.*;

class MysqlCommentRepoTest {
	RepoTest testrepo = new RepoTest();
	MysqlAccountRepo account_repo = new MysqlAccountRepo();
	MysqlProjectRepo project_repo = new MysqlProjectRepo();
	MysqlIssueRepo issue_repo = new MysqlIssueRepo(); 
	MysqlCommentRepo comment_repo = new MysqlCommentRepo(); 
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
	Issue issue1 = new Issue("issue1", "description1", Priority.MINOR, State.NEW, tester1);
	Issue issue2 = new Issue("issue2", "description2", Priority.MINOR, State.NEW, tester1);
	Comment comment1 = new Comment("content1", tester1);
	Comment comment2 = new Comment("content2", tester2);
	Comment comment3 = new Comment("content3", pl1);
	Comment comment4 = new Comment("content4", dev1);
	Comment comment5 = new Comment("content5", dev2);
	
    @BeforeEach
    void beforeEach() {
    	testrepo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	account_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	project_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	issue_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
    	comment_repo.setJDBC("org.sqlite.JDBC", "jdbc:sqlite:test.sqlite3");
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
    	
    	issue1.setId(1);
    	issue2.setId(2);
    	
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
		
		// 이슈 추가
		issue_repo.add(project1, issue1);
		issue_repo.add(project1, issue2);
		
		// 커맨트 추가
		comment_repo.add(issue1, comment1);
		comment_repo.add(issue1, comment2);
		comment_repo.add(issue1, comment3);
		comment_repo.add(issue2, comment4);
		comment_repo.add(issue2, comment5);
    }
	
	@AfterEach
	public void afterEach() {
	}
	
	
	@Test
	void find() {
		List<Comment>comments = comment_repo.findAll(issue1);
		Assertions.assertEquals(3, comments.size());
	}

}
