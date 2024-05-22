//package test.repository;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import main.domain.*;
//import main.repository.CommentRepo;
//import main.repository.MysqlCommentRepo;
//
//class MysqlCommentRepoTest {
//	CommentRepo commentrepo = new MysqlCommentRepo();
//	
//    @BeforeEach
//    void init() {
//    }
//	
//	@AfterEach
//	public void afterEach() {
//	}
//	
//	@Test
//	void add() {
//		Issue issue = new Issue("title1", "descript1");
//		issue.setId(1);
//		User user = new Tester("tester2", "tester2");
//		Comment comment = new Comment();
//		comment.setContent("comment1 comment1 comment1 comment1");
//		comment.setWriter(user);
//		commentrepo.add(user, issue, comment);
//	}
//	
//	@Test
//	void find() {
//		Issue issue = new Issue("title1", "descript1");
//		issue.setId(1);
//    	for(Comment cm : commentrepo.find(issue)) {
//    		System.out.println("~~~~ cm ~~~");
//    		System.out.println(cm.getContent());
//		};
//	}
//
//}
