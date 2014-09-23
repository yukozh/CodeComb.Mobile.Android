
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:17:18
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.broadcast;

import android.content.Context;

import com.codecomb.ServerLocator;
import com.codecomb.module.base.Base;
import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public class BroadcastServiceImpl implements BroadcastService {

	
	
	private BroadcastRepository broadcastRepository;
	
	public BroadcastServiceImpl(){
		
		broadcastRepository = ServerLocator.getInstance(BroadcastRepository.class);
		
	}

	@Override
	public Base sendBroadcast(Context context, String content,String accessToken)
			throws AppException {
		// TODO Auto-generated method stub
		return broadcastRepository.sendBroadcast(context, content, accessToken);
	}


	
	
	
	
	
	
	
}

