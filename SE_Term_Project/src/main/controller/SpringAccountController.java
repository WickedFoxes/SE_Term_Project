package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.domain.enumeration.Authority;
import main.model.AccountModel;

@Controller
public class SpringAccountController extends AccountController{
	public SpringAccountController(AccountModel model) {
		super(model);
	}

	@GetMapping("/signup")
	public String signupPage() {
		if(model.getUser() == null) return "redirect:/login";
		if(model.getUser().getAuthority() != Authority.ADMIN) return "redirect:/project";
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signupHadler(
			@RequestParam(value="accountID") String accountID,
			@RequestParam(value="password") String password,
			@RequestParam(value="authority") String authority) {
		if(model.getUser() == null) return "redirect:/login";
		boolean flag = model.trySignup(accountID, password, Authority.valueOf(authority));
		
		if(flag) return "redirect:/project";
		return "redirect:/signup";
	}

}
