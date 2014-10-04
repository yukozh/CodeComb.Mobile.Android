
/**
 * @author UFreedom
 * @since 2014 2014-9-23 下午11:40:15
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.clarifications;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.codecomb.MyApplication;
import com.codecomb.ServerLocator;
import com.codecomb.SettingsManager;
import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;



public class ClarificationManager {
	
	private static Context context;
	private ClarificationService clarificationService;
	private String accessToken;

	
	
	static {
		context = MyApplication.getInstance().getApplicationContext();
	}
	
	
	public static  ClarificationManager getInstance(){
		
		return SingletonCreator.instance;
		
	}
	
	private static class SingletonCreator{
		private static ClarificationManager instance = new ClarificationManager();
	}
	
	
	
	private ClarificationManager(){
		clarificationService = ServerLocator.getInstance(ClarificationService.class);
		accessToken = SettingsManager.getInstance().getAccessToken();

	}
	
	
	
	
	public List<Clarification> getClarifications(int contestID){
		
		List<Clarification> clarifications = new ArrayList<Clarification>();		
		
		try {
			clarifications = clarificationService.getClarifications(context, accessToken,contestID);
		
		
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		
		return clarifications;
		
	}
	
	
	
	
	public Base responseClarifications(int cardID,String answer,int status) throws AppException{
		

		return clarificationService.responseClarification(context, accessToken, cardID, answer, status);
		
	}
	
	

}

