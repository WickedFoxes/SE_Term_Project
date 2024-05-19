package main.domain;

import java.time.LocalDateTime;

public class Comment {
	private String content;
	private LocalDateTime writtenDate;
	private Account wirter;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	public void setWrittenDate(LocalDateTime writtenDate) {
		this.writtenDate = writtenDate;
	}
	public Account getWirter() {
		return wirter;
	}
	public void setWirter(Account wirter) {
		this.wirter = wirter;
	}
}
