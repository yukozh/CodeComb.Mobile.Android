
/**
 * @author UFreedom
 * @since 2014 2014-10-4 上午8:57:42
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.im;

import java.util.ArrayList;
import java.util.List;

import com.codecomb.MyApplication;
import com.codecomb.ServerLocator;
import com.codecomb.SettingsManager;
import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;

import android.content.Context;



public class MessageManager {
	
	
	
	private static Context context;
	private String accessToken;
	private MessageService messageService;
	
	
	static{
		context = MyApplication.getInstance().getApplicationContext();
	}
	
	
	
	public static MessageManager getInstance(){
		
		return SingletonCreator.instance;
	}
	
	private static class SingletonCreator{
		
		private static MessageManager instance = new MessageManager();
	}
	
	private  MessageManager(){
		
		accessToken = SettingsManager.getInstance().getAccessToken();
		messageService = ServerLocator.getInstance(MessageService.class);
		
	}
	
	public Base sendMessage(int userID,String content) {
		
		Base base = new Base();
		
		try {
			
		  	base = messageService.sendMesssage(context, accessToken, userID, content);
			
		} catch (AppException e) {
			e.printStackTrace();
			
		}
		
		return base;
		
	}
	
	public List<Message> getChatRecords(int  userId){
		
		List<Message> list = new ArrayList<Message>();
		
		try {
			list = messageService.getChatRecords(context, accessToken, userId);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	

}

