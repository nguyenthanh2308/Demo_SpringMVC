package com.abc.entities;

public class Follow {
	private int following_user_id;
	private int followed_user_id;
	private String created_at;
	
	
	public Follow(int following_user_id, int followed_user_id, String created_at) {
		super();
		this.following_user_id = following_user_id;
		this.followed_user_id = followed_user_id;
		this.created_at = created_at;
	}
	
	
	public int getFollowing_user_id() {
		return following_user_id;
	}
	public void setFollowing_user_id(int following_user_id) {
		this.following_user_id = following_user_id;
	}
	public int getFollowed_user_id() {
		return followed_user_id;
	}
	public void setFollowed_user_id(int followed_user_id) {
		this.followed_user_id = followed_user_id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	
	
}