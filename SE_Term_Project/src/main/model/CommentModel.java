package main.model;

import java.util.List;

import main.domain.Comment;
import main.repository.CommentRepo;

public class CommentModel extends Model{
	private CommentRepo comment_repo;
	public CommentModel(SystemManager s, CommentRepo crepo) {
		super(s);
		this.comment_repo = crepo;
	}
	
	public void createComment(Comment comment) {
		comment_repo.add(getIssue(), comment);
	}
	
	public List<Comment> getCommentList(){
		return comment_repo.findAll(getIssue());
	}

}
