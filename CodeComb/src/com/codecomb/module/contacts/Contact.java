package com.codecomb.module.contacts;

import java.util.List;

import com.codecomb.module.base.Base;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact extends Base {

	private static final long serialVersionUID = 1L;
	
	
	@SerializedName("UserID")
	private int userID;
	
	
	@SerializedName("NickName")
	private String nickName;
	
	
	
	@SerializedName("UnreadMessageCount")
	private int unreadMessageCount;
	
	
	
	@SerializedName("AvatarURL")
	private String avatarURL;
	
	
	@SerializedName("List")
	private List<Contact> contacts;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getUnreadMessageCount() {
		return unreadMessageCount;
	}

	public void setUnreadMessageCount(int unreadMessageCount) {
		this.unreadMessageCount = unreadMessageCount;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	
	
	
	
	
	
	
	
	
}
