package main.repository;
import main.domain.Comment;
import main.domain.Issue;
import main.domain.User;

import java.util.List;

public interface CommentRepo {
	void add(User user, Issue issue, Comment comment);
	List<Comment> find(Issue issue);
}
