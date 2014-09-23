
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午4:35:46
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.repository;

import java.io.File;

import android.content.Context;

import com.codecomb.MyApplication;
import com.codecomb.infrastructure.respository.wabapi.net.ApiClient;
import com.codecomb.module.login.Auth;
import com.codecomb.module.profile.Profile;
import com.codecomb.module.profile.ProfileRepository;
import com.codecomb.utils.Utils;
import com.codecomb.xceptions.AppException;



public class ProfileRepositoryImpl implements ProfileRepository{

	
	
	
	@Override
	public Profile getProfile(Context context, String accessToken) throws AppException {
	
		
		return ApiClient.getProfile(context, accessToken);
	}
	
	

	
	

}

