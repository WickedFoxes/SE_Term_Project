package main.repository;

import java.sql.Timestamp;
import java.util.List;

import main.domain.Dev;
import main.domain.FilterOption;
import main.domain.Issue;
import main.domain.Project;
import main.domain.User;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;

public interface IssueRepo {
	void add(Project project, Issue issue);
	void setAssignee(Issue issue, Dev assignee);
	void setFixer(Issue issue, Dev fixer);
	List<Issue> findAll(Project project);
	List<Issue> findAll(Project project, User user);
	List<Issue> findAll(Project project, User user, FilterOption option);
	void setState(Issue issue, State state);
	void setPriority(Issue issue, Priority priority);
	void setResolvedDate(Issue issue);
	List<User> sortByRecommendScore(List<User> users);
}