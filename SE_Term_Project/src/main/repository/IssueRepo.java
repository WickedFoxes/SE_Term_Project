package main.repository;

import java.util.List;

import main.domain.Dev;
import main.domain.FilterOption;
import main.domain.Issue;
import main.domain.Project;
import main.domain.User;
import main.domain.enumeration.State;

public interface IssueRepo {
	void add(Project project, Issue issue);
	void setAssignee(Issue issue, Dev assignee);
	void setFixer(Issue issue, Dev fixer);
	List<Issue> find(User user, Project project);
	List<Issue> find(User user, Project project, FilterOption option);
	void setState(Issue issue, State state);
}
