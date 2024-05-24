package main.application;

import main.controller.SwingLoginController;
import main.domain.Admin;
import main.model.LoginModel;
import main.model.SystemManager;
import main.repository.MysqlAccountRepo;
import main.view.SwingMainView;

public class SwingApplication implements Application{
	public void run() {
		MysqlAccountRepo accountRepo = new MysqlAccountRepo();
		Admin admin = new Admin("admin", "admin");
		accountRepo.add(admin);
		SystemManager systemData = new SystemManager();
		
		SwingMainView view = new SwingMainView();
		
		LoginModel model = new LoginModel(systemData, accountRepo);
		SwingLoginController controller = new SwingLoginController(view.getLoginView(), model);
		
		//SwingProjectListView view = new SwingProjectListView();
	}
}
