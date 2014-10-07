package com.codecomb.infrastructure.wabapi.net;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;
import com.codecomb.module.clarifications.Clarification;
import com.codecomb.module.contacts.Contact;
import com.codecomb.module.contests.Contest;
import com.codecomb.module.im.Message;
import com.codecomb.module.login.Auth;
import com.codecomb.module.profile.Profile;
import com.codecomb.utils.GsonUtils;

public class ApiClient {

	private static final String TAG = ApiClient.class.getSimpleName();


	public static Auth auth(Context context, String username, String passsword)
			throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Username", username);
		params.put("Password", passsword);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("Auth"), params);

		return GsonUtils.createGson().fromJson(jsonRsp, Auth.class);

	}

	public static Base responseClarification(Context context,
			String accessToken, int clarID, String answer, int status)
			throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("ClarID", clarID);
		params.put("Status", status);
		params.put("Answer", answer);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("ResponseClarification"), params);

		return GsonUtils.createGson().fromJson(jsonRsp, Base.class);

	}

	public static Profile getProfile(Context context, String accessToken)
			throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("GetProfile"), params);

		return GsonUtils.createGson().fromJson(jsonRsp, Profile.class);

	}

	public static Contact findContacts(Context context, String accessToken,
			String nickName) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("Nickname", nickName);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("FindContacts"), params);

		return GsonUtils.createGson().fromJson(jsonRsp, Contact.class);

	}

	public static Base registPushService(Context context, String accessToken,
			String divceToken) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("DeviceType", 1);
		params.put("DeviceToken", divceToken);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("FindContacts"), params);

		return GsonUtils.createGson().fromJson(jsonRsp, Base.class);

	}

	public static Base broadCast(Context context, String accessToken,
			String message) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("Message", message);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("BroadCast"), params);

		return GsonUtils.createGson().fromJson(jsonRsp, Base.class);

	}

	public static List<Contact> getContacts(Context context, String accessToken)
			throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("GetContacts"), params);

		Log.e(TAG, "Contact:" + jsonRsp);
		
	

		return GsonUtils.createGson().fromJson(jsonRsp, Contact.class)
				.getContacts();

	}

	public static List<Contest> getContests(Context context, String accessToken)
			throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("GetContests"), params);



		return GsonUtils.createGson().fromJson(jsonRsp, Contest.class)
				.getContests();

	}

	public static List<Contest> getContests(Context context,
			String accessToken, int page) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("Page", page);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("GetContests"), params);


		return GsonUtils.createGson().fromJson(jsonRsp, Contest.class).getContests();

	}

	public static List<Contest> getManagerContests(Context context,
			String accessToken, int page) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("Page", page);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("GetManagedContests"), params);

		return GsonUtils.createGson().fromJson(jsonRsp, Contest.class)
				.getContests();

	}
	
	public static List<Contest> getManagerContests(Context context,
			String accessToken) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("GetManagedContests"), params);


		Log.e(TAG, "ManagerContests:" + jsonRsp);


		return GsonUtils.createGson().fromJson(jsonRsp, Contest.class)
				.getContests();

	}


	public static List<Message> getChatRecords(Context context,
			String accessToken, int userID) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("UserID", userID);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("GetChatRecords"), params);

	

		return GsonUtils.createGson().fromJson(jsonRsp, Message.class)
				.getMessages();

	}

	public static Base sendMessage(Context context, String accessToken,
			int userID, String content) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("UserID", userID);
		params.put("Content", content);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("SendMessage"), params);

		
		return GsonUtils.createGson().fromJson(jsonRsp, Base.class);

	}

	public static Base loginByBarCode(Context context, String accessToken,
			String barCode) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("BarCode", barCode);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("LoginByBarCode"), params);

		return GsonUtils.createGson().fromJson(jsonRsp, Base.class);

	}

	
	public static List<Clarification> getClarifications(Context context,
			String accessToken, int contestId) throws AppException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Token", accessToken);
		params.put("ContestID", contestId);

		String jsonRsp = NetClientFactory.getClient().doPost(context,
				getRequestUrl("GetClarifications"), params);

		
		return GsonUtils.createGson().fromJson(jsonRsp, Clarification.class).getClarifications();

	}
	
	
	

	private static String getRequestUrl(String revUrl) {
		return Constants.BASE_ADDRESS_TERMINAL_SERVICE + revUrl;
	}

}
