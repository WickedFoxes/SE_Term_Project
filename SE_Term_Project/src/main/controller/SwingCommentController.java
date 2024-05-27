package main.controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.domain.Comment;
import main.domain.FilterOption;
import main.domain.User;
import main.model.CommentModel;
import main.model.Observer;
import main.view.SwingIssueDetailView;

public class SwingCommentController extends SwingController {
	private CommentModel model;
	private SwingIssueDetailView view;
	
	public SwingCommentController(SwingIssueDetailView view, CommentModel model) {
		this.view = view;
		this.view.setWriteListener(new WriteButtonListener());
		this.model = model;
		setObserver();
	}
	
	private void setObserver() {
		model.subscribe(new Observer() {
			public void update() {
				if(!view.getAccessableViewNames().contains(view.requestGetCurrentViewName())) return;
				updateComments();
			}
		});
	}
	
	private void updateComments() {
		if(model.getIssue() == null) return;
		List<Comment> comments = model.getCommentList();
		System.out.println("num comments: "+comments.size());
		view.updateComments(comments);
	}
	
	private class WriteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String content = view.getCommentContent();
			User user = model.getUser();
			model.createComment(new Comment(content, user));
			model.notifyObservers();
			view.clearCommentTextField();
		}
	}
}
