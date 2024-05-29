package main.model;

import java.util.List;

import main.domain.Dev;
import main.domain.Issue;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;
import main.repository.IssueRepo;
import main.repository.ProjectRepo;

public class IssueModel extends Model {
	private IssueRepo issue_repo;
	private ProjectRepo project_repo;
	public IssueModel(SystemManager s, IssueRepo r, ProjectRepo p) {
		super(s);
		this.issue_repo = r;
		this.project_repo = p;
	}
	public void modify(Issue issue, Priority priroty, State state, Dev assignee) {
		// priority, state, assignee 수정 권한
		if(getUser().getAuthority() == Authority.PL) {
			if(state != null) issue_repo.setState(issue, state);
			if(priroty != null) issue_repo.setPriority(issue, priroty);
			if(assignee != null) issue_repo.setAssignee(issue, assignee);
		}
		
		if(getUser().getAuthority() == Authority.DEV) {
			if(issue.getState() == State.ASSIGNED && state == State.FIXED) {
				issue_repo.setState(issue, state);
				issue_repo.setFixer(issue, issue.getAssignee());
			}
		}
		if(getUser().getAuthority() == Authority.TESTER) {
			if(issue.getState() == State.FIXED && state == State.RESOLVED) {
				issue_repo.setState(issue, state);
				issue_repo.setResolvedDate(issue);
			}
		}
	}
	
	//Return true if acceptable(valid) modify occurred (if new properties are NULL, then return true)
	public boolean tryModify(Issue currentIssue, Priority newPriorty, State newState, Dev newAssignee) {
		if(getUser().getAuthority() == Authority.PL) return tryModify_PL(currentIssue, newPriorty, newState, newAssignee);
		else if(getUser().getAuthority() == Authority.DEV) return tryModify_Dev(currentIssue, newPriorty, newState, newAssignee);
		else if(getUser().getAuthority() == Authority.TESTER) return tryModify_Tester(currentIssue, newPriorty, newState, newAssignee);
		else return false;
	}
	
	private boolean tryModify_PL(Issue currentIssue, Priority newPriorty, State newState, Dev newAssignee) {
		issue_repo.setPriority(currentIssue, newPriorty);
		
		//PL set State [RESOLVED -> CLOSED]
		if(currentIssue.getState() == State.RESOLVED && newState == State.CLOSED) {
			issue_repo.setState(currentIssue, newState);
			return true;
		}
		//PL set Assignee [NEW -> ASSIGNED]
		else if (currentIssue.getState() == State.NEW  && newState == State.ASSIGNED) { 
			issue_repo.setState(currentIssue, newState);
			issue_repo.setAssignee(currentIssue, newAssignee);
			return true;
		}
		else return (currentIssue.getPriority() != newPriorty);
	}
	
	private boolean tryModify_Dev(Issue currentIssue, Priority newPriorty, State newState, Dev newAssignee) {
		if(currentIssue.getState() != State.ASSIGNED || newState != State.FIXED) return false;
		
		//Dev set State [ASSIGNED -> FIXED]
		issue_repo.setState(currentIssue, newState);
		issue_repo.setFixer(currentIssue, (Dev)getUser());
		return true;
	}
	
	private boolean tryModify_Tester(Issue currentIssue, Priority newPriorty, State newState, Dev newAssignee) {
		if(currentIssue.getState() != State.FIXED || newState != State.RESOLVED) return false;
		
		//Tester set State [FIXED -> RESOLVED]
		issue_repo.setState(currentIssue, newState);
		return true;
	}
	
	public List<User> getRecommedAssignee(){
		List<User> devs = project_repo.findAll(getProject(), Authority.DEV);
		return issue_repo.sortByRecommendScore(devs);
	}
}
