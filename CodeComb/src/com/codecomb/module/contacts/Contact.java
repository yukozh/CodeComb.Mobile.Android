package com.codecomb.module.contacts;

import java.util.List;

import com.codecomb.module.base.Base;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact extends Base {

	private static final long serialVersionUID = 1L;
	
	
	@SerializedName("UserID")
	private int userID;
	
	
	@SerializedName("Nickname")
	private String nickName;
	
	@SerializedName("Motto")
	private  String motto;
	
	
	@SerializedName("UnreadMessageCount")
	private int unreadMessageCount;
	
	
	
	@SerializedName("AvatarURL")
	private String avatarURL;
	
	
	@SerializedName("List")
	private List<Contact> contacts;
	
	private String acatarPath;
	
	

	public String getAcatarPath() {
		return acatarPath;
	}

	public void setAcatarPath(String acatarPath) {
		this.acatarPath = acatarPath;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}
	
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

	
	
	@Override
	public String toString() {
		return "Contact [userID=" + userID + ", nickName=" + nickName
				+ ", motto=" + motto + ", unreadMessageCount="
				+ unreadMessageCount + ", avatarURL=" + avatarURL
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
