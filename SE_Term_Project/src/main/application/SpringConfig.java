package main.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import main.model.*;
import main.repository.*;

@Configuration
public class SpringConfig {	
	@Bean
	public LoginModel loginModel() {
		return new LoginModel(sysmanager(), accountRepo());
	}

	@Bean SystemManager sysmanager() {
		return new SystemManager();
	}
	
	@Bean
	public AccountRepo accountRepo() {
		return new MysqlAccountRepo();
	}
	
	@Bean
	public AccountModel AccountModel() {
		return new AccountModel(sysmanager(), accountRepo());
	}
}
