package main.repository;
import main.domain.Comment;
import main.domain.Issue;
import main.domain.User;

import java.util.List;

public interface CommentRepo {
	void add(Issue issue, Comment comment);
	List<Comment> findAll(Issue issue);
}
