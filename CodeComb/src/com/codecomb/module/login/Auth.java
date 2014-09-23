package com.codecomb.module.login;

import com.codecomb.module.base.Base;
import com.google.gson.annotations.SerializedName;

public class Auth extends Base {

	private static final long serialVersionUID = 1L;

	@SerializedName("AccessToken")
	private String accessToken;



	public Auth() {

	}

	


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "[" + "AccessToken:" + accessToken + " Code:" + this.getCode()
				+ " isSuccess:" + this.isSuccess() + " info:  " + this.getInfo()
				+ "" + "" + "]";
	}

}
