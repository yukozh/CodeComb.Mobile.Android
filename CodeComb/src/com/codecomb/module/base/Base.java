package com.codecomb.module.base;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Base implements Serializable {
	

	private static final long serialVersionUID = 1L;

	
	@SerializedName("Code")
	public String code;

	
	@SerializedName("Info")
	public String info;
	

	
	@SerializedName("IsSuccess")
	public boolean isSuccess;


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
