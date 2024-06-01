package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.domain.Admin;
import main.domain.User;
import main.model.LoginModel;


@Controller
public class SpringLoginController{
	private LoginModel login_model;
	public SpringLoginController(LoginModel model) {
		this.login_model = model;
	}
	
	@GetMapping("/")
	public String home() {
		if(login_model.getUser() == null) return "redirect:/login";
		return "redirect:/project";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginHandler(
			@RequestParam(value="accountID") String accountID,
			@RequestParam(value="password") String password) {
		boolean flag = this.login_model.tryLogin(accountID, password);
		if(flag) return "redirect:/project";
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		this.login_model.logout();
		return "redirect:/login";
	}
}
