package com.codecomb.module.base;



public class Response {


	public String Code;
	
	public String Info;
	public boolean IsSuccess;
	
	
	
	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	
	

	public String getInfo() {
		return Info;
	}

	public void setInfo(String info) {
		Info = info;
	}

	public boolean isSuccess() {
		return IsSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		IsSuccess = isSuccess;
	}

//	public boolean isSuccess() {
//		return Constants.RESULT_CODE_SUCCESS.equals(Code);
//	}
//	
	
	
	


	
}
