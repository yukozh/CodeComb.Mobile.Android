
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午2:08:05
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.login;

import android.content.Context;

import com.codecomb.module.base.Base;
import com.codecomb.xceptions.AppException;

public interface LoginService {
	
	public Auth login(Context context,String username,String password)throws AppException;
	public Base logout(Context context) throws AppException;


}

