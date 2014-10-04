package com.codecomb.module.profile;

import com.codecomb.module.base.Base;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile extends Base  {

	
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	@SerializedName("UserID")
	private int userId;
	
	
	@SerializedName("Nickname")
	private String nickname;
	
	
	 
	@SerializedName("Rating")
	private int rating;
	
	
	
	@SerializedName("Motto")
	private String motto;
	
	
	
	
	@SerializedName("AvatarURL")
	private String avatarURL;



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public String getMotto() {
		return motto;
	}



	public void setMotto(String motto) {
		this.motto = motto;
	}



	public String getAvatarURL() {
		return avatarURL;
	}


	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}




	
}
