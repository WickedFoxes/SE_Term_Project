package main.domain;

import main.domain.enumeration.State;

public class FilterOption {
	private State state;
	private Tester reporter;
	private Dev assignee;
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Tester getReporter() {
		return reporter;
	}
	public void setReporter(Tester reporter) {
		this.reporter = reporter;
	}
	public Dev getAssignee() {
		return assignee;
	}
	public void setAssignee(Dev assignee) {
		this.assignee = assignee;
	} 
}