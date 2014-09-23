
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午4:34:07
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.profile;

import android.content.Context;

import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public interface ProfileService {
	
	
	public Profile getProfile(Context context,String accessToken) throws AppException;



}

