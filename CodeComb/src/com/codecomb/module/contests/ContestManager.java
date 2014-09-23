/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午11:58:56
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.contests;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.codecomb.MyApplication;
import com.codecomb.ServerLocator;
import com.codecomb.SettingsManager;
import com.codecomb.xceptions.AppException;

public class ContestManager {

	private static Context context;
	private ContestService contestService;
	private String accessToken;

	static {
		context = MyApplication.getInstance().getApplicationContext();

	}

	public static ContestManager getInstance() {
		return SingletonCreator.instance;
	}

	private static class SingletonCreator {

		private static final ContestManager instance = new ContestManager();

	}
	
	
	private ContestManager(){
		contestService = ServerLocator.getInstance(ContestService.class);
		accessToken = SettingsManager.getInstance().getAccessToken();
	}
	
	public List<Contest> getContests(){
		
		List<Contest> contests = new ArrayList<Contest>();
		
		try {
			contests = contestService.getContests(context, accessToken);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contests;
		
	}
	
	public List<Contest> getContests(int page){
		
		List<Contest> contests = new ArrayList<Contest>();
		
		try {
			contests = contestService.getContests(context, accessToken,page);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contests;
		
	}
	
	
	

}
