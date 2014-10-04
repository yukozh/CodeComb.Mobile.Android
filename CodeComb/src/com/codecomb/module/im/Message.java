package com.codecomb.module.im;

import java.util.Date;
import java.util.List;

import com.codecomb.module.base.Base;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message extends Base {

	private static final long serialVersionUID = 1L;
	
	
	
	@Expose
	@SerializedName("SenderID")
	private int senderID ;
	
	@Expose
	@SerializedName("ReceiverID")
	private int receiverID ;
	
	@Expose
	@SerializedName("Time")
	private Date time ;
	
	@Expose
	@SerializedName("Content")
	private String Content ;
	
	@Expose
	@SerializedName("List")
    private List<Message> messages;

	
	
	
	
	public int getSenderID() {
		return senderID;
	}

	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}

	public int getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	

    
    
}
