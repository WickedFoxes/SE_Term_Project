package main.domain;

import java.time.LocalDateTime;

public class Comment {
	private String content;
	private LocalDateTime writtenDate;
	private User wirter;
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
	public User getWriter() {
		return wirter;
	}
	public void setWriter(User wirter) {
		this.wirter = wirter;
	}
}
