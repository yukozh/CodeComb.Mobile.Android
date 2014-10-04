/**
 * @author UFreedom
 * @since 2014 2014-9-23 下午11:39:37
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.broadcast;

import com.codecomb.MyApplication;
import com.codecomb.ServerLocator;
import com.codecomb.SettingsManager;
import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;

import android.content.Context;

public class BroadcastManager {

	private static Context context;
	private BroadcastService broadcastService;
	private String accessToken;

	static {
		context = MyApplication.getInstance().getApplicationContext();
	}

	private BroadcastManager() {
		broadcastService = ServerLocator.getInstance(BroadcastService.class);
		accessToken = SettingsManager.getInstance().getAccessToken();
	}

	public static  BroadcastManager getInstance() {
		return SingletonCreator.instance;
	}

	private static class SingletonCreator {
		private static BroadcastManager instance = new BroadcastManager();

	}

	public boolean sendBroadcast(String message) {

		try {

			return broadcastService
					.sendBroadcast(context, message, accessToken).isSuccess()
					? true
					: false;

		} catch (AppException e) {
			e.printStackTrace();
			return false;
		}

	}

}
