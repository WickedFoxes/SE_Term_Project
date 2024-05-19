package main.domain;

import main.domain.enumeration.State;

public class FilterOption {
	private State state;
	private Account reporter;
	private Account assignee;
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Account getReporter() {
		return reporter;
	}
	public void setReporter(Account reporter) {
		this.reporter = reporter;
	}
	public Account getAssignee() {
		return assignee;
	}
	public void setAssignee(Account assignee) {
		this.assignee = assignee;
	} 
}