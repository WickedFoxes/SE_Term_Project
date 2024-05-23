package main.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "main")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringApp implements Application{
	private String[] args;
	public SpringApp(String[] args) {
		this.args = args;
	}
	
	@Override
	public void run() {
		SpringApplication.run(SpringApp.class, args);
		System.out.println("어플리케이션을 시작합니다~~");
	}
}
