package main.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.domain.Dev;
import main.domain.FilterOption;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;
import main.model.IssueListModel;
import main.model.ProjectListModel;

@Controller
public class SpringIssueListController {
	private IssueListModel issuelist_model;
	private ProjectListModel projectlist_model;
	public SpringIssueListController(IssueListModel imodel, ProjectListModel pmodel) {
		this.issuelist_model = imodel; 
		this.projectlist_model = pmodel; 
	}

	@RequestMapping(value="/issueList/{project_id}", method=RequestMethod.GET)
	public String issueListPage(@PathVariable("project_id") int project_id
			,Model input) {
		if(projectlist_model.getUser() == null) return "redirect:/login";
		projectlist_model.setIssue(null);
		
		for(Project p : projectlist_model.getProjectList()) {
			if(p.getId() == project_id) {
				projectlist_model.setProject(p); break;
			}
		}
		
		if(issuelist_model.getProject() == null) return "projectList"; 
		input.addAttribute("issues", issuelist_model.getIssueList());
		input.addAttribute("authority", projectlist_model.getUser().getAuthority().name());
		input.addAttribute("project_id", project_id);
		
		return "issueList";
	}
	
	@RequestMapping(value="/issueList/{project_id}", method=RequestMethod.POST)
	public String issueListFilterPage(@PathVariable("project_id") int project_id
			,@RequestParam(value="state") String state
			,@RequestParam(value="assignee") String assignee
			,@RequestParam(value="reporter") String reporter
			,Model input) {
		if(projectlist_model.getUser() == null) return "redirect:/login";
		projectlist_model.setIssue(null);
		
		for(Project p : projectlist_model.getProjectList()) {
			if(p.getId() == project_id) {
				projectlist_model.setProject(p); break;
			}
		}
		
		if(issuelist_model.getProject() == null) return "projectList"; 
		
		State state_input = null;
		Tester reporter_input = null;
		Dev assignee_input = null;
		projectlist_model.getAllAcounts(Authority.DEV);
		
		
		for(User d : projectlist_model.getAllAcounts(Authority.DEV)) {
			if(d.getAccountID().equals(assignee)) {
				assignee_input = (Dev)d; break;
			}
		}
		
		for(User t : projectlist_model.getAllAcounts(Authority.TESTER)) {
			if(t.getAccountID().equals(reporter)) {
				reporter_input = (Tester)t; break;
			}
		}
		boolean isState = Arrays.stream(State.values()).anyMatch(v -> v.name().equals(state));
		if(isState) state_input = State.valueOf(state);
		
		FilterOption filter = new FilterOption(state_input, reporter_input, assignee_input);
		input.addAttribute("issues", issuelist_model.getIssueList(filter));
		input.addAttribute("authority", projectlist_model.getUser().getAuthority().name());
		input.addAttribute("project_id", project_id);
		
		return "issueList";
	}
	
	
	@RequestMapping(value="/issueList/{project_id}/create", method=RequestMethod.GET)
	public String issueListCteatePage(@PathVariable("project_id") int project_id
			,Model input) {
		if(projectlist_model.getUser() == null) return "redirect:/login";
		if(projectlist_model.getProject() == null) return "redirect:/projectList";
		if(projectlist_model.getUser().getAuthority() != Authority.TESTER) 
			return "redirect:/issueList/"+Integer.toString(project_id);
		
		input.addAttribute("project_id", project_id);
		
		return "issueCreate";
	}
	
	@RequestMapping(value="/issueList/{project_id}/create", method=RequestMethod.POST)
	public String issueListCteateHandler(@PathVariable("project_id") int project_id,
			@RequestParam(value="title") String title,
			@RequestParam(value="priority") String priority,
			@RequestParam(value="description") String description) {
		if(projectlist_model.getUser() == null) return "redirect:/login";
		if(projectlist_model.getProject() == null) return "redirect:/projectList";
		if(projectlist_model.getUser().getAuthority() != Authority.TESTER) 
			return "redirect:/issueList/"+Integer.toString(project_id);
		
		boolean isPriority = Arrays.stream(Priority.values()).anyMatch(v -> v.name().equals(priority));

		if(isPriority && issuelist_model.tryCreateIssue(title, description, Priority.valueOf(priority)))
			return "redirect:/issueList/"+Integer.toString(project_id);
		
		return "redirect:/issueList/"+Integer.toString(project_id)+"/create";
	}
}
