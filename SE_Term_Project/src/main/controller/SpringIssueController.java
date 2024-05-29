package main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.domain.Dev;
import main.domain.Issue;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;
import main.model.AccountModel;
import main.model.CommentModel;
import main.model.IssueListModel;
import main.model.IssueModel;
import main.model.ProjectListModel;

@Controller
public class SpringIssueController {
	private ProjectListModel projectlist_model;
	private IssueModel issue_model;
	private IssueListModel issuelist_model;
	private CommentModel comment_model;
	
	public SpringIssueController(ProjectListModel plmodel, IssueModel imodel, IssueListModel ilmodel, CommentModel cmodel) {
		this.projectlist_model = plmodel;
		this.issue_model = imodel;
		this.issuelist_model = ilmodel;
		this.comment_model = cmodel;
	}
	
	@RequestMapping(value="/issue/{issue_id}", method=RequestMethod.GET)
	public String issuePage(@PathVariable("issue_id") int issue_id, Model input) {
		if(issue_model.getUser() == null) return "redirect:/login";
		if(issue_model.getProject() == null) return "redirect:/projectList";
		
		for(Issue is : issuelist_model.getIssueList()) {
			if(is.getId() == issue_id) {
				issue_model.setIssue(is); break;
			}
		};
		List<String> priorities = new ArrayList<>();
		for(Priority p :Priority.values()) {
			priorities.add(p.name());
		}
		List<String> states = new ArrayList<>();
		for(State st :State.values()) {
			states.add(st.name());
		}
		
		List<User> recommend = issue_model.getRecommedAssignee();
		int recommend_size = recommend.size() > 3 ? 3 : recommend.size();
		
		input.addAttribute("project_id", issue_model.getProject().getId());
		input.addAttribute("priorities", priorities);
		input.addAttribute("states", states);
		input.addAttribute("authority", issue_model.getUser().getAuthority().name());
		input.addAttribute("issue", issue_model.getIssue());
		input.addAttribute("comments", comment_model.getCommentList());
		input.addAttribute("devs", projectlist_model.getAllAccountsInProject(Authority.DEV));
		input.addAttribute("recommends", recommend.subList(0, recommend_size));
		
		return "issue";
	}
	
	@RequestMapping(value="/issue/{issue_id}/edit", method=RequestMethod.POST)
	public String issueHandler(@PathVariable("issue_id") int issue_id, 
			@RequestParam(value="state") String state,
			@RequestParam(value="priority") String priority,
			@RequestParam(value="assignee") String assignee,
			Model input) {
		if(issue_model.getUser() == null) return "redirect:/login";
		if(issue_model.getProject() == null) return "redirect:/projectList";
		if(issue_model.getIssue() == null) 
			return "redirect:/issueList/" + Integer.toString(issue_model.getProject().getId());
		
		Priority p = null;
		State s = null;
		Dev d = null;
		
		if(priority != null && !priority.equals("none")) 
			p = Priority.valueOf(priority);
		if(state != null && !state.equals("none")) 
			s = State.valueOf(state);
		if(assignee != null && !assignee.equals("none")) {
			for(User user : projectlist_model.getAllAccountsInProject(Authority.DEV)) {
				if(user.getAccountID().equals(assignee)) {
					d = (Dev)user; break;
				}
			}
		}
		
		issue_model.modify(issue_model.getIssue(), p, s, d);
		
		List<String> priorities = new ArrayList<>();
		for(Priority pr :Priority.values()) {
			priorities.add(pr.name());
		}
		List<String> states = new ArrayList<>();
		for(State st :State.values()) {
			states.add(st.name());
		}
		
		input.addAttribute("project_id", issue_model.getProject().getId());
		input.addAttribute("priorities", priorities);
		input.addAttribute("states", states);
		input.addAttribute("authority", issue_model.getUser().getAuthority().name());
		input.addAttribute("issue", issue_model.getIssue());
		input.addAttribute("comments", comment_model.getCommentList());
		input.addAttribute("devs", projectlist_model.getAllAccountsInProject(Authority.DEV));
		
		return "redirect:/issue/" + Integer.toString(issue_id);
	}
}
