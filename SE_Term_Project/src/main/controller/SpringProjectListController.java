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
import main.model.ProjectListModel;

@Controller
public class SpringProjectListController extends ProjectListContorller{
	public SpringProjectListController(ProjectListModel model) {
		super(model);
	}

	@GetMapping("/project")
	public String projectPage(Model input) {
		if(model.getUser() == null) return "redirect:/login";
		model.setIssue(null);
		input.addAttribute("projects", model.getProjectList());
		input.addAttribute("authority", model.getUser().getAuthority().name());
		return "projectList";
	}

	@GetMapping("/project/create")
	public String projectCreatePage(Model input) {
		if(model.getUser() == null) return "redirect:/login";
		if(model.getUser().getAuthority() != Authority.ADMIN) return "redirect:/project";
		
		input.addAttribute("pls", model.getAllAcounts(Authority.PL));
		input.addAttribute("devs", model.getAllAcounts(Authority.DEV));
		input.addAttribute("testers", model.getAllAcounts(Authority.TESTER));
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
		for(User user : model.getAllAcounts(Authority.PL)) {
			if(pl.equals(user.getAccountID())) 
				projectleader = (ProjectLeader)user;
		}
		
		List<Dev> dev_list = new ArrayList<>();
		for(User user : model.getAllAcounts(Authority.DEV)) {
			for(String d : devs) {
				if(d.equals(user.getAccountID())) dev_list.add((Dev)user);
			}
		}
		
		List<Tester> tester_list = new ArrayList<>();
		for(User user : model.getAllAcounts(Authority.TESTER)) {
			for(String t : testers) {
				if(t.equals(user.getAccountID())) tester_list.add((Tester)user);
			}
		}
		
		if(model.tryCreateProject(name, projectleader, dev_list, tester_list)) return "redirect:/project";
		
		return "redirect:/project/create";
	}
}
