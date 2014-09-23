
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午4:55:28
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.contests;

import java.util.List;

import android.content.Context;

import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public interface ContestRepository {
	 
	public List<Contest> getContests(Context contect,String  accessToken)throws AppException;
	

	public List<Contest> getContests(Context context,String  accessToken,int page) throws AppException;
	
	public List<Contest> getManagedContests(Context context,String  accessToken,int page) throws AppException;
	
	public List<Contest> getManagedContests(Context context,String  accessToken) throws AppException;
	
	
	
}

