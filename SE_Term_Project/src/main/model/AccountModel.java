package main.model;

import main.domain.Dev;
import main.domain.ProjectLeader;
import main.domain.SystemManager;
import main.domain.Tester;
import main.domain.enumeration.Authority;
import main.repository.AccountRepo;

public class AccountModel extends Model {
	private AccountRepo repo;
	
	public AccountModel(SystemManager s, AccountRepo r) {
		super(s);
		this.repo = r;
	}
	
	public void signup(String id, String pw, Authority authority) {
		switch(authority) {
//		case PL:
//			ProjectLeader pl = new ProjectLeader(id, pw);
//			repo.add(pl);
//			break;
//		case DEV:
//			Dev dev = new Dev(id, pw);
//			repo.add(dev);
//			break;
//		case TESTER:
//			Tester tester = new Tester(id, pw);
//			repo.add(tester);
//			break;
//		default:
//			//error
		}
	}
}
