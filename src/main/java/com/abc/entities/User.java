package com.abc.entities;

public class User {
    private int id;
    private String username;
    private String passWord;
    private String createdAt;

    public User(int id, String username, String passWord, String createdAt) {
    	super();
    	this.id = id;
    	this.username = username;
    	this.passWord = passWord;
    	this.createdAt = createdAt;
    }
    public User(String username,String passWord) {
        this.username = username;
        this.passWord = passWord;
        
    }

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


}
