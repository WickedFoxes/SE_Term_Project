package main.domain;

import main.domain.enumeration.State;

public class FilterOption {
	private State state;
	private Tester reporter;
	private Dev assignee;
	
	public FilterOption(State state, Tester reporter, Dev assignee) {
		this.state = state;
		this.reporter = reporter;
		this.assignee = assignee;
	}
	
	public Tester getReporter() {
		return reporter;
	}
	
	public Dev getAssignee() {
		return assignee;
	}
	
	public State getState() {
		return state;
	} 
}