package com.abc.entities;



public class Post {
	private int id;
	private String title;
	private String body;
	private int userId;
	private String status;
	private String created_At;
	
	public Post(int id, String title, String body, int userId, String status, String createAt) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.userId = userId;
		this.status = status;
		this.created_At = createAt;
	}
	
	
	
	public Post(String title, String body, int userId, String status) {
		super();
		this.title = title;
		this.body = body;
		this.userId = userId;
		this.status = status;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCreateAt() {
		return created_At;
	}
	public void setCreateAt(String createAt) {
		this.created_At = createAt;
	}
	
	
	
}
