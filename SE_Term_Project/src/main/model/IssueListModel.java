package main.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
	private FilterOption filterOption;
		
	public IssueListModel(SystemManager s, IssueRepo r) {
		super(s);
		this.repo = r;
		this.filterOption = new FilterOption(null, null, null);
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
	
	public List<Issue> getIssuesCreatedWithinLastWeek() {
		List<Issue> issues = repo.findAll(getProject());
		List<Issue> recentIssues = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Timestamp oneWeekAgo = new Timestamp(calendar.getTimeInMillis());

        for (Issue issue : issues) {
            if (issue.getReportedDate().after(oneWeekAgo)) recentIssues.add(issue);
        }
		return recentIssues;
	}
	
	public List<Issue> getIssuesResolvedWithinLastWeek() {
		List<Issue> issues = repo.findAll(getProject());
		List<Issue> recentIssues = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Timestamp oneWeekAgo = new Timestamp(calendar.getTimeInMillis());

        for (Issue issue : issues) {
        	if(issue.getResolvedDate() == null) continue;
            if (issue.getResolvedDate().after(oneWeekAgo)) recentIssues.add(issue);
        }
		return recentIssues;
	}
	
	public void setFilterOption(FilterOption filterOption) {
		this.filterOption = filterOption;
	}
	
	public FilterOption getFilterOption() {
		return this.filterOption;
	}
}
