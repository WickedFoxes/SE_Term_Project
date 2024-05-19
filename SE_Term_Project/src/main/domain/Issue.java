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
	private Tester reporter;
	private Dev assignee;
	private Dev fixer;
	public Issue(String title, String description, Priority priority, State state, 
			Tester reporter, Dev assignee, Dev fixer) {
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
	public Dev getFixer() {
		return fixer;
	}
	public void setFixer(Dev fixer) {
		this.fixer = fixer;
	}
}
