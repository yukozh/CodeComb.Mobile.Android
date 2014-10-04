
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午2:09:40
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.login;

import android.content.Context;

import com.codecomb.ServerLocator;
import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;


public class LoginServiceImpl implements LoginService {

	
	private LoginRepository loginRepository;
	
	public LoginServiceImpl(){
		
		loginRepository = ServerLocator.getInstance(LoginRepository.class);
	}
	
	@Override
	public Auth login(Context context,String username,String password) throws AppException {

		return loginRepository.login(context, username,password);

	}

	@Override
	public Base logout(Context context) throws AppException {

		return loginRepository.logout(context);
	}

}

