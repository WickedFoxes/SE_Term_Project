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
	
}
