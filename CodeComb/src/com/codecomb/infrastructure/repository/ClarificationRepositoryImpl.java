
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:27:16
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
import com.codecomb.module.clarifications.Clarification;
import com.codecomb.module.clarifications.ClarificationRepository;



public class ClarificationRepositoryImpl implements ClarificationRepository {

	
	
	@Override
	public List<Clarification> getClarifications(Context context,String accessToken,
			int contestID) throws AppException {
		return ApiClient.getClarifications(context, accessToken, contestID);
	}

	
	
	@Override
	public Base responseClarification(Context context,String accessToken, int cardID,
			String answer, int status) throws AppException {
		return ApiClient.responseClarification(context, accessToken, cardID, answer, status);
		
	}

	

}

