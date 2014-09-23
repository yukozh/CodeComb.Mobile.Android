
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午2:58:15
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.repository;

import android.content.Context;

import com.codecomb.infrastructure.respository.wabapi.net.ApiClient;
import com.codecomb.module.base.Base;
import com.codecomb.module.login.Auth;
import com.codecomb.module.login.LoginRepository;
import com.codecomb.xceptions.AppException;



public class LoginRepositoryImpl implements LoginRepository {

	@Override
	public Auth login(Context context, String username,String password) throws AppException {
	
		return ApiClient.auth(context, username,password);
		
	}

	@Override
	public Base logout(Context context) throws AppException {


		return null;
	}

}

