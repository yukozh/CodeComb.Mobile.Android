/**
 * @author UFreedom
 * @since 2014 上午12:20:24
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */
package com.codecomb;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class SettingsManager implements OnSharedPreferenceChangeListener {

	
	
	
	
	private static Context context;
	
	
	private static final String KEY_USERNMAE = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_IS_STORE_USER = "storeuser";
	private static final String KEY_ACCESS_TOKEN = "accesstoken";
	
	public static SettingsManager getInstance(){
		return SingletonCreator.instance;
	}
	
	
	
	private static  class SingletonCreator{
		private static final SettingsManager instance = new SettingsManager();
	}
	
	
	private SettingsManager(){
		context = MyApplication.getInstance().getApplicationContext();
	}
	
	
	
	public void setAccessToken(String accessToken){
		setString(KEY_ACCESS_TOKEN, accessToken);
	}
	
	public String getAccessToken(){
		return getString(KEY_ACCESS_TOKEN, "");

	}
	
	public void setUsername(String name){
		setString(KEY_USERNMAE, name);
	}
	
	public void setPassword(String password){
		setString(KEY_PASSWORD, password);
	}
	
	public void storeUser(String name,String password,String accessToken ){
		setUsername(name);
		setPassword(password);
		setAccessToken(accessToken);
	}
	
	public boolean isStoreUser(){
		return getBoolean(KEY_IS_STORE_USER);
	}
	
	public void isStoreUser(boolean value){
		setBoolean(KEY_IS_STORE_USER, value);
	}
	
	
	public String getUsername(){
		return getString(KEY_USERNMAE, "");
	}
	
	public String getPassword(){
		return getString(KEY_PASSWORD, "");
	}
	
	
	
	
	

	private static SharedPreferences getSharedPreferences(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

	private static String getString(String key, String def) {
		return getSharedPreferences(context).getString(key, def);
	}

	private static void setString( String key, String value) {
		Editor editor = getSharedPreferences(context).edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	
	private static boolean getBoolean(String key) {
		return getSharedPreferences(context).getBoolean(key, false);
	}

	private static void setBoolean( String key, boolean value) {
		Editor editor = getSharedPreferences(context).edit();
		editor.putBoolean(key, value);
		editor.commit();
	}


	private SharedPreferences getSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(MyApplication
				.getInstance());
	}

	private static void setInt(String key, int value) {
		Editor editor = getSharedPreferences(context).edit();
		editor.putInt(key, value);
		editor.commit();
	}

	private String getString(int key, String def) {
		return getSharedPreferences().getString(
				MyApplication.getInstance().getString(key), def);
	}

	private String getString(int key, int def) {
		return getString(key, MyApplication.getInstance().getString(def));
	}

	private void setString(int key, String value) {
		Editor editor = getSharedPreferences().edit();
		editor.putString(MyApplication.getInstance().getString(key), value);
		editor.commit();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		// TODO Auto-generated method stub

	}

}
