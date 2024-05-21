package test.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.repository.MysqlIssueRepo;
import main.repository.IssueRepo;
import main.domain.*;
import main.domain.enumeration.*;

class MysqlIssueRepoTest{
	IssueRepo repository = new MysqlIssueRepo();
	
    @BeforeEach
    void init() {
    }
	
	@AfterEach
	public void afterEach() {
	}
	
	@Test
	void add() {
    	Tester tester1 = new Tester("tester1", "tester1");
    	Issue issue = new Issue("issue9999", "tesr issue2~~", Priority.BLOCKER, State.NEW, tester1, null, null);
    	Project project = new Project("project1");
    	project.setId(1);
//		repository.add(project,issue);
//		Assertions.assertEquals(repository.getAccount("admin"), 1);
	}

	@Test
	void setAssignee() {
		Tester tester1 = new Tester("tester1", "tester1");
    	Dev dev1 = new Dev("dev1", "dev1");
    	Issue issue = new Issue("issue9999", "tesr issue2~~", Priority.BLOCKER, State.NEW, tester1, null, null);
    	issue.setId(4);
		repository.setAssignee(issue, dev1);
//		Assertions.assertEquals(repository.getAccount("admin"), 1);
	}
	
	@Test
	void setState() {
		Tester tester1 = new Tester("tester1", "tester1");
    	Dev dev1 = new Dev("dev1", "dev1");
    	Issue issue = new Issue("issue9999", "tesr issue2~~", Priority.BLOCKER, State.NEW, tester1, null, null);
    	issue.setId(4);
		repository.setState(issue, State.ASSIGNED);
	}
	
	@Test
	void find() {
		Project project = new Project("project1");
    	project.setId(1);
    	User tester1 = new Dev("tester1", "tester1");
    	User tester2 = new Dev("tester2", "tester2");
    	User pl1 = new ProjectLeader("pl1", "pl1");
    	User Dev1 = new Dev("dev1", "dev1");
    	
    	System.out.println("############## find ##############");
    	for(Issue is : repository.find(pl1, project)) {
    		System.out.println("~~~~ pl ~~~");
    		System.out.println(is.getTitle());
		};
		for(Issue is : repository.find(tester1, project)) {
    		System.out.println("~~~~ tester1 ~~~");
    		System.out.println(is.getTitle());
		};
    	for(Issue is : repository.find(tester2, project)) {
    		System.out.println("~~~~ tester2 ~~~");
    		System.out.println(is.getTitle());
		};
		for(Issue is : repository.find(Dev1, project)) {
			System.out.println("~~~~ dev ~~~");
			System.out.println(is.getTitle());
		};
	}
	
	@Test
	void findFiltering() {
		Project project = new Project("project1");
    	project.setId(1);

    	User tester1 = new Tester("tester1", "tester1");
    	User tester2 = new Tester("tester2", "tester2");
    	User pl1 = new ProjectLeader("pl1", "pl1");
    	User dev1 = new Dev("dev1", "dev1");

    	FilterOption option = new FilterOption();
    	option.setAssignee((Dev)dev1);
    	option.setReporter((Tester)tester1);
    	option.setState(State.NEW);
    	
    	System.out.println("############## filtering find ##############");
    	for(Issue is : repository.find(pl1, project, option)) {
    		System.out.println("~~~~ pl ~~~");
    		System.out.println(is.getTitle());
		};
		for(Issue is : repository.find(tester1, project, option)) {
    		System.out.println("~~~~ tester1 ~~~");
    		System.out.println(is.getTitle());
		};
    	for(Issue is : repository.find(tester2, project, option)) {
    		System.out.println("~~~~ tester2 ~~~");
    		System.out.println(is.getTitle());
		};
		for(Issue is : repository.find(dev1, project, option)) {
			System.out.println("~~~~ dev ~~~");
			System.out.println(is.getTitle());
		};
	}
}
