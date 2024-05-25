package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.domain.Comment;
import main.model.CommentModel;
import main.model.IssueModel;

@Controller
public class SpringCommentController {
	private IssueModel issue_model;
	private CommentModel comment_model;
	public SpringCommentController(IssueModel imodel, CommentModel cmodel) {
		this.issue_model = imodel;
		this.comment_model = cmodel;
	}
	
	@RequestMapping(value="/comment/{issue_id}/create", method=RequestMethod.POST)
	public String commentHandler(@PathVariable("issue_id") int issue_id,
			@RequestParam(value="content") String content) {
		if(comment_model.getUser() == null) return "redirect:/login";
		if(comment_model.getProject() == null) return "redirect:/projectList";
		if(comment_model.getIssue() == null) return "redirect:/projectList";
		
		Comment c = new Comment(content, comment_model.getUser());
		comment_model.createComment(c);
		
		return "redirect:/issue/"+Integer.toString(issue_id);
	}
	
	
}
