package main.model;

import java.util.ArrayList;
import java.util.List;

import main.domain.*;
import main.domain.enumeration.*;
import main.repository.AccountRepo;

public class AccountModel extends Model {
	private AccountRepo repo;
	
	public AccountModel(SystemManager s, AccountRepo r) {
		super(s);
		this.repo = r;
	}
	
	public boolean trySignup(String id, String pw, Authority authority){
		if(getUser() == null) return false;  
		if(getUser().getAuthority() != Authority.ADMIN) return false;
		if(repo.contains(id)) return false;
		if(id.equals("")) return false;
		if(pw.equals("")) return false;
		
		switch(authority) {
		case PL:
			ProjectLeader pl = new ProjectLeader(id, pw);
			repo.add(pl);
			break;
		case DEV:
			Dev dev = new Dev(id, pw);
			repo.add(dev);
			break;
		case TESTER:
			Tester tester = new Tester(id, pw);
			repo.add(tester);
			break;
		default:
			return false;
		}
		return true;
	}
	
	public List<User> getAllAccounts(Authority authority){
		return repo.findAll(authority);
	}
}
