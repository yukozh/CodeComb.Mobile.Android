
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:21:43
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.repository;

import android.content.Context;

import com.codecomb.exceptions.AppException;
import com.codecomb.infrastructure.wabapi.net.ApiClient;
import com.codecomb.module.base.Base;
import com.codecomb.module.broadcast.BroadcastRepository;
import com.codecomb.module.login.Auth;



public class BroadcastRepositoryImpl implements BroadcastRepository {

	@Override
	public Base sendBroadcast(Context context, String content,String accessToken)
			throws AppException {
		
		return ApiClient.broadCast(context, accessToken, content);
		
	}



}

