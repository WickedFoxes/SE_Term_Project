package main.domain;

import java.time.LocalDateTime;

import main.domain.enumeration.Priority;
import main.domain.enumeration.State;

public class Issue {
	private String title;
	private String description;
	private LocalDateTime reportedDate;
	private Priority priority;
	private State state;
	private Account reporter;
	private Account assignee;
	private Account fixer;
	public Issue(String title, String description, Priority priority, State state, 
			Account reporter, Account assignee,Account fixer) {
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.state = state;
		this.setReporter(reporter);
		this.setAssignee(assignee);
		this.setFixer(fixer);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(LocalDateTime reportedDate) {
		this.reportedDate = reportedDate;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
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
	public Account getFixer() {
		return fixer;
	}
	public void setFixer(Account fixer) {
		this.fixer = fixer;
	}
}
