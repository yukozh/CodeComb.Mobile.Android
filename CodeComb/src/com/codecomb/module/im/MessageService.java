
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午4:45:47
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



public interface MessageService {
	
	
	public Base sendMesssage(Context context,Auth auth,String content) throws AppException;

}

