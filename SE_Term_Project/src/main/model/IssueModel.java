package main.model;

import main.domain.Dev;
import main.domain.Issue;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;
import main.repository.IssueRepo;

public class IssueModel extends Model {
	private IssueRepo repo;
	public IssueModel(SystemManager s, IssueRepo r) {
		super(s);
		this.repo = r;
	}
	public void modify(Issue issue, Priority priroty, State state, Dev assignee) {
		// priority, state, assignee 수정 권한
		if(getUser().getAuthority() == Authority.PL) {
			if(state != null) repo.setState(issue, state);
			if(priroty != null) repo.setPriority(issue, priroty);
			if(assignee != null) repo.setAssignee(issue, assignee);
		}
		
		if(getUser().getAuthority() == Authority.DEV) {
			if(issue.getState() == State.ASSIGNED && state == State.FIXED) {
				repo.setState(issue, state);
				repo.setFixer(issue, issue.getAssignee());
			}
		}
		if(getUser().getAuthority() == Authority.TESTER) {
			if(issue.getState() == State.FIXED && state == State.RESOLVED) 
				repo.setState(issue, state);
		}
	}
	
	//Return true if actual modify occurred (if new properties are NULL, then return false)
	public boolean tryModify(Issue currentIssue, Priority newPriorty, State newState, Dev newAssignee) {
		if(getUser().getAuthority() == Authority.PL) return tryModify_PL(currentIssue, newPriorty, newState, newAssignee);
		else if(getUser().getAuthority() == Authority.DEV) return tryModify_Dev(currentIssue, newPriorty, newState, newAssignee);
		else if(getUser().getAuthority() == Authority.TESTER) return tryModify_Tester(currentIssue, newPriorty, newState, newAssignee);
		else return false;
	}
	
	private boolean tryModify_PL(Issue currentIssue, Priority newPriorty, State newState, Dev newAssignee) {
		if(newPriorty != null) repo.setPriority(currentIssue, newPriorty);
		
		if(newState != null) {
			if(currentIssue.getState() == State.RESOLVED && newState == State.CLOSED) { //PL set State [RESOLVED -> CLOSED]
				repo.setState(currentIssue, newState);
				return true;
			}
			else return false; //Any other state change should not be accepted
		}
		
		if(newAssignee != null) {
			if(currentIssue.getState() == State.NEW) { //PL set Assignee [NEW -> ASSIGNED]
				repo.setState(currentIssue, State.ASSIGNED);
				repo.setAssignee(currentIssue, newAssignee);
				return true;
			}
			else return false; //If state is not NEW, assignee change should not be accepted
		}
		
		return (newPriorty != null);
	}
	
	private boolean tryModify_Dev(Issue currentIssue, Priority newPriorty, State newState, Dev newAssignee) {
		if(currentIssue.getState() == State.ASSIGNED && newState == State.FIXED) { //Dev set State [ASSIGNED -> FIXED]
			repo.setState(currentIssue, newState);
			repo.setFixer(currentIssue, (Dev)getUser());
			return true;
		}
		return false; //Any other change should not be accepted
	}
	
	private boolean tryModify_Tester(Issue currentIssue, Priority newPriorty, State newState, Dev newAssignee) {
		if(currentIssue.getState() == State.FIXED && newState == State.RESOLVED) { //Tester set State [FIXED -> RESOLVED]
			repo.setState(currentIssue, newState);
			return true;
		}
		return false; //Any other change should not be accepted
	}
}
