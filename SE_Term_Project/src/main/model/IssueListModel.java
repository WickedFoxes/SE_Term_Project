package main.model;

import java.util.ArrayList;
import java.util.List;

import main.domain.Issue;
import main.domain.Project;
import main.domain.User;
import main.repository.IssueRepo;

public class IssueListModel extends Model {
	private IssueRepo repo;
		
	public IssueListModel(SystemManager s, IssueRepo r) {
		super(s);
		this.repo = r;
	}

	public void createIssue() {
		
	}
	
	public List<Issue> getIssueList(){
		Project project = getProject();
		User user = getUser();
		return new ArrayList<Issue>();
	}
	
}
