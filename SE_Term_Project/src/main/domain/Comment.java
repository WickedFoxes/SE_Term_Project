package main.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Comment {
	private int id;
	private String content;
	private Timestamp writtenDate;
	private User wirter;
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
		return wirter;
	}
	public void setWriter(User wirter) {
		this.wirter = wirter;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
