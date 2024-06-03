package main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.domain.*;
import main.domain.enumeration.Authority;
import main.model.AccountModel;
import main.model.ProjectListModel;

@Controller
public class SpringProjectListController{
	private ProjectListModel project_model;
	private AccountModel account_model;
	public SpringProjectListController(ProjectListModel pmodel, AccountModel amodel) {
		this.project_model = pmodel;
		this.account_model = amodel;
	}

	@GetMapping("/project")
	public String projectPage(Model input) {
		if(project_model.getUser() == null) return "redirect:/login";
		project_model.setIssue(null);
		input.addAttribute("projects", project_model.getProjectList());
		input.addAttribute("authority", project_model.getUser().getAuthority().name());
		return "projectList";
	}

	@GetMapping("/project/create")
	public String projectCreatePage(Model input) {
		if(project_model.getUser() == null) return "redirect:/login";
		if(project_model.getUser().getAuthority() != Authority.ADMIN) return "redirect:/project";
		
		input.addAttribute("pls", account_model.getAllAccounts(Authority.PL));
		input.addAttribute("devs", account_model.getAllAccounts(Authority.DEV));
		input.addAttribute("testers", account_model.getAllAccounts(Authority.TESTER));
		return "projectCreate";
	}
	
	@RequestMapping(value="/project/create", method=RequestMethod.POST)
	public String projectCreateHandler(
			@RequestParam(value="name") String name,
			@RequestParam(value="pl") String pl,
			@RequestParam(value="dev") String dev,
			@RequestParam(value="tester") String tester) {
		String[] devs = dev.split(" ");
		String[] testers = tester.split(" ");
		
		ProjectLeader projectleader = null;
		for(User user : account_model.getAllAccounts(Authority.PL)) {
			if(pl.equals(user.getAccountID())) 
				projectleader = (ProjectLeader)user;
		}
		
		List<Dev> dev_list = new ArrayList<>();
		for(User user : account_model.getAllAccounts(Authority.DEV)) {
			for(String d : devs) {
				if(d.equals(user.getAccountID())) dev_list.add((Dev)user);
			}
		}
		
		List<Tester> tester_list = new ArrayList<>();
		for(User user : account_model.getAllAccounts(Authority.TESTER)) {
			for(String t : testers) {
				if(t.equals(user.getAccountID())) tester_list.add((Tester)user);
			}
		}
		
		if(project_model.tryCreateProject(name, projectleader, dev_list, tester_list)) 
			return "redirect:/project";
		
		return "redirect:/project/create";
	}
}
