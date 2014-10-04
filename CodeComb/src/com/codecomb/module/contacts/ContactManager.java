
/**
 * @author UFreedom
 * @since 2014 2014-9-23 下午11:40:37
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.contacts;

import java.util.ArrayList;
import java.util.List;

import com.codecomb.MyApplication;
import com.codecomb.ServerLocator;
import com.codecomb.SettingsManager;
import com.codecomb.exceptions.AppException;

import android.content.Context;



public class ContactManager {
	
	
	private static Context context;
	private ContactService contactService;
	private String accessToken;
	
	
	
	
	
	
	static{
		context = MyApplication.getInstance().getApplicationContext();

	}
	
	
	public static ContactManager getInstance(){
		return SingletonCreator.instance;
	}
	
	
	private static class SingletonCreator{
		private static ContactManager instance = new ContactManager();
	}
	
	private ContactManager(){
		
		contactService = ServerLocator.getInstance(ContactService.class);
		accessToken = SettingsManager.getInstance().getAccessToken();
		
	}
	
	
	
	public List<Contact> getContacts() {
		
		List<Contact> list = new ArrayList<Contact>();
		
		try {
			list.addAll(contactService.getContacts(context, accessToken));
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	
	
	
	
	
	

}

