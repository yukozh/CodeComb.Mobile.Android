
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

import java.util.List;

import android.content.Context;

import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;
import com.codecomb.module.login.Auth;



public interface MessageService {
	
	
	public Base sendMesssage(Context context,String accessToken,int otherUserID,String content) throws AppException;

	public List<Message> getChatRecords(Context context,String accessToken,int otherUserID)throws AppException;

}

