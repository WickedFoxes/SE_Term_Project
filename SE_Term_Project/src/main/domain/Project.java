package main.domain;
import java.time.LocalDateTime;

public class Project {
	private String name;
	private LocalDateTime createdDate;
	public Project(String name) {
		this.name = name;
		this.createdDate = LocalDateTime.now(); 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
}
