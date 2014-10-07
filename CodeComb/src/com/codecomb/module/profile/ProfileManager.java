/**
 * @author UFreedom
 * @since 2014 2014-9-20 上午12:18:55
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.profile;

import java.io.File;

import com.codecomb.MyApplication;
import com.codecomb.ServerLocator;
import com.codecomb.SettingsManager;
import com.codecomb.exceptions.AppException;
import com.codecomb.utils.Utils;

import android.content.Context;

public class ProfileManager {

	private static Context context;
	private String accessToken;
	private Profile profile;

	static {
		context = MyApplication.getInstance().getApplicationContext();
	}

	private ProfileService profileService;

	public static ProfileManager getInstance() {
		return SingletonCreator.instance;
	}

	private static class SingletonCreator {
		private static ProfileManager instance = new ProfileManager();

	}

	private ProfileManager() {
		profileService = ServerLocator.getInstance(ProfileService.class);
		accessToken = SettingsManager.getInstance().getAccessToken();
	}

	public void getProfileFromService() {

		profile = new Profile();

		try {

			profile = profileService.getProfile(context, accessToken);

			
			
			
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Profile getProfile() {

		if (profile == null) {

			getProfileFromService();

		}

		return profile;
	}

	

}
