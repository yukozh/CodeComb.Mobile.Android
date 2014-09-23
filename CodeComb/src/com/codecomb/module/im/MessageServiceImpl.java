
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午4:48:49
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.im;

import android.content.Context;

import com.codecomb.ServerLocator;
import com.codecomb.module.base.Base;
import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public class MessageServiceImpl implements MessageService {

	
	
	MessageRepository messageRepository;
	
	public MessageServiceImpl(){
		
		messageRepository = ServerLocator.getInstance(MessageRepository.class);
		
	}

	@Override
	public Base sendMesssage(Context context, Auth auth, String content)
			throws AppException {

		return messageRepository.sendMesssage(context, auth, content)
				;
	}
	
	
	

}

