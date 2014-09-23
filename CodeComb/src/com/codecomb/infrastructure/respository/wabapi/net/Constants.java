package com.codecomb.infrastructure.respository.wabapi.net;

public class Constants {
	
	
	
	public static final String BASE_ADDRESS_TERMINAL_SERVICE = "http://www.codecomb.net/API/";
	public static final int MAX_RETRY_TIMES = 3;
	public static final int INTERVAL_RETRY = 100;

	public static final int TIMEOUT_SOCKET = 10 * 1000;
	public static final int TIMEOUT_CONNECTION = 1 * 1000;
	public static final String CONTENT_CHARSET = "UTF-8";

	public static final String CONTENT_TYPE = "application/json";

	public static final String RESULT_CODE_SUCCESS = "0";
	public static final String RESULT_CODE_ERROR_SERVER_INTERNAL = "300";
	public static final String RESULT_CODE_ERROR_CLIENT = "400";
	public static final String RESULT_CODE_ERROR_BUSINESS_LOGIC = "500";
	public static final String ERR_CODE_PASSWORD_INCORRECT = "500001";
	public static final String ERR_CODE_USER_ALREADY_EXISTS = "500002";
	public static final String ERR_CODE_USER_NOT_EXISTS = "500003";
}
