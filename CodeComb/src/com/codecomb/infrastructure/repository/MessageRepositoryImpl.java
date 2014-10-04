
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午4:48:23
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.repository;

import java.util.List;

import android.content.Context;

import com.codecomb.exceptions.AppException;
import com.codecomb.infrastructure.wabapi.net.ApiClient;
import com.codecomb.module.base.Base;
import com.codecomb.module.im.Message;
import com.codecomb.module.im.MessageRepository;
import com.codecomb.module.login.Auth;



public class MessageRepositoryImpl implements MessageRepository {

	

	@Override
	public Base sendMesssage(Context context, String accessToken,
			int otherUserID, String content) throws AppException {
		
		return ApiClient.sendMessage(context, accessToken, otherUserID, content);
	}

	@Override
	public List<Message> getChatRecords(Context context, String accessToken,
			int otherUserID) throws AppException {
		
		return ApiClient.getChatRecords(context, accessToken, otherUserID);
	}

}

