package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.domain.enumeration.Authority;
import main.model.AccountModel;

@Controller
public class SpringAccountController{
	private AccountModel account_model;
	public SpringAccountController(AccountModel model) {
		this.account_model = model;
	}

	@GetMapping("/signup")
	public String signupPage() {
		if(account_model.getUser() == null) return "redirect:/login";
		if(account_model.getUser().getAuthority() != Authority.ADMIN) return "redirect:/project";
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signupHadler(
			@RequestParam(value="accountID") String accountID,
			@RequestParam(value="password") String password,
			@RequestParam(value="authority") String authority) {
		if(account_model.getUser() == null) return "redirect:/login";
		boolean flag = account_model.trySignup(accountID, password, Authority.valueOf(authority));
		
		if(flag) return "redirect:/project";
		return "redirect:/signup";
	}

}
