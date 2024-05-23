package main.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Comment {
	private int id;
	private String content;
	private Timestamp writtenDate;
	private User writer;
	public Comment(String content, User writer) {
		this.content=content;
		this.writer=writer;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getWrittenDate() {
		return writtenDate;
	}
	public void setWrittenDate(Timestamp writtenDate) {
		this.writtenDate = writtenDate;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User wirter) {
		this.writer = wirter;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
