package com.codecomb.module.contests;

import java.util.Date;
import java.util.List;

import com.codecomb.module.base.Base;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Contest extends Base {
	

	private static final long serialVersionUID = 1L;


	
	@SerializedName("ContestID")
	private int contestID;
	
	
	
	@SerializedName("Title")
	private String title;
	
	
	@SerializedName("Format")
	private String format;
	
	
	@SerializedName("FormatAsInt")	
	private int formatAsInt;
	
	
	@SerializedName("Begin")
	private Date begin;

	
	
    @SerializedName("End")
	private Date end;
	
	
	@SerializedName("RestBegin")
	private Date restBegin;
	
	
	
	@SerializedName("RestEnd")
	private Date restEnd;
	
	
	
	@SerializedName("List")
	private List <Contest> contests ;

	
	
	
	
	
	
	
	

	
	
	public int getContestID() {
		return contestID;
	}


	public void setContestID(int contestID) {
		this.contestID = contestID;
	}





	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}




	public String getFormat() {
		return format;
	}






	public void setFormat(String format) {
		this.format = format;
	}

	public int getFormatAsInt() {
		return formatAsInt;
	}






	public void setFormatAsInt(int formatAsInt) {
		this.formatAsInt = formatAsInt;
	}






	public Date getBegin() {
		return begin;
	}




	public void setBegin(Date begin) {
		this.begin = begin;
	}




	public Date getEnd() {
		return end;
	}





	public void setEnd(Date end) {
		this.end = end;
	}




	public Date getRestBegin() {
		return restBegin;
	}





	public void setRestBegin(Date restBegin) {
		this.restBegin = restBegin;
	}




	public Date getRestEnd() {
		return restEnd;
	}





	public void setRestEnd(Date restEnd) {
		this.restEnd = restEnd;
	}






	public List<Contest> getContests() {
		return contests;
	}


	public void setContests(List<Contest> contests) {
		this.contests = contests;
	}


	@Override
	public String toString() {
	

		return "[" +
				"ContestID: " + contestID +
				" Title:" + title +
				" Format:" + format +
				" FormatAsInt: " +formatAsInt+
				"" +
				"" +
				"" +
				"]";
	}

	
	
	
	
	
	
	
	
}
