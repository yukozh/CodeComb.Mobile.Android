package com.codecomb.module.clarifications;

import java.util.Date;
import java.util.List;

import com.codecomb.module.base.Base;
import com.google.gson.annotations.SerializedName;

public class Clarification extends Base {

	private static final long serialVersionUID = 1L;
	
	
	
	@SerializedName("ClarID")
	private int clarID ;
	
	
	@SerializedName("Question")
	private String question;
	
	
	@SerializedName("Answer")
	private String answer ;
	
	
	@SerializedName("Status")
	private String status ;
	
	
	@SerializedName("StatusAsInt")
	private int statusAsInt ;
	
	
	@SerializedName("Category")
	private String category ;
	
	
	@SerializedName("Time")
	private Date time ;
	
	
	@SerializedName("ContestID")
	private int contestID ;
    
	
	
	@SerializedName("List")
    private List<Clarification> clarifications;

	
	
	
	
	
	
	
	
	

	public int getClarID() {
		return clarID;
	}


	public void setClarID(int clarID) {
		this.clarID = clarID;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getStatusAsInt() {
		return statusAsInt;
	}


	public void setStatusAsInt(int statusAsInt) {
		this.statusAsInt = statusAsInt;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	public int getContestID() {
		return contestID;
	}


	public void setContestID(int contestID) {
		this.contestID = contestID;
	}


	public List<Clarification> getClarifications() {
		return clarifications;
	}


	public void setClarifications(List<Clarification> clarifications) {
		this.clarifications = clarifications;
	}


	
	@Override
	public String toString() {
		return "Clarification [clarID=" + clarID + ", question=" + question
				+ ", answer=" + answer + ", status=" + status
				+ ", statusAsInt=" + statusAsInt + ", category=" + category
				+ ", time=" + time + ", contestID=" + contestID
				+   "]";
	}
    
   
 
    
	
}
