package com.codecomb.module.base;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Base implements Serializable {
	

	private static final long serialVersionUID = 1L;

	
	@SerializedName("Code")
	private String code;

	
	@SerializedName("Info")
	private String info;
	

	
	@SerializedName("IsSuccess")
	private boolean isSuccess;


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public boolean isSuccess() {
		return isSuccess;
	}


	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	
	
	


}
