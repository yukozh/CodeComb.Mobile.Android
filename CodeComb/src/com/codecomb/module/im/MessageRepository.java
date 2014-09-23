
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午4:45:38
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.im;

import android.content.Context;

import com.codecomb.module.base.Base;
import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public interface MessageRepository {
	
	
	public Base sendMesssage(Context context,Auth auth,String content) throws AppException;

	
	
}

