package main.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import main.model.*;
import main.repository.*;

@Configuration
public class SpringConfig {	
	@Bean SystemManager sysmanager() {
		return new SystemManager();
	}
	
	@Bean
	public AccountRepo accountRepo() {
		return new MysqlAccountRepo();
	}

	@Bean
	public ProjectRepo projectRepo() {
		return new MysqlProjectRepo();
	}
	
	@Bean
	public IssueRepo issueRepo() {
		return new MysqlIssueRepo();
	}
	
	@Bean
	public AccountModel accountModel() {
		return new AccountModel(sysmanager(), accountRepo());
	}
	
	@Bean
	public LoginModel loginModel() {
		return new LoginModel(sysmanager(), accountRepo());
	}
	
	@Bean
	public ProjectListModel projectListModel() {
		return new ProjectListModel(sysmanager(), projectRepo(), accountRepo());
	}
	
	@Bean
	public IssueListModel issueListModel() {
		return new IssueListModel(sysmanager(), issueRepo());
	}
}
