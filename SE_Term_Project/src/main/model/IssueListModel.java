package main.model;

import java.util.ArrayList;
import java.util.List;

import main.domain.FilterOption;
import main.domain.Issue;
import main.domain.Project;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Priority;
import main.repository.IssueRepo;

public class IssueListModel extends Model {
	private IssueRepo repo;
		
	public IssueListModel(SystemManager s, IssueRepo r) {
		super(s);
		this.repo = r;
	}

	public boolean tryCreateIssue(String title, String description, Priority priority) {
		User user = getUser();
		if(!(user instanceof Tester)) return false;
		
		Project project = getProject();
		Issue issue = new Issue(title, description, priority, (Tester)user);
		repo.add(project, issue);
		return true;
	}
	
	public List<Issue> getIssueList(){
		Project project = getProject();
		User user = getUser();
		List<Issue> issueList = repo.findAll(project, user);
		return issueList;
	}
	
	public List<Issue> getIssueList(FilterOption option){
		Project project = getProject();
		User user = getUser();
		List<Issue> issueList = repo.findAll(project, user, option);
		return issueList;
	}
}
