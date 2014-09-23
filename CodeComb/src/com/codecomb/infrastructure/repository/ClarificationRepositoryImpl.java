
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

import com.codecomb.module.base.Base;
import com.codecomb.module.clarifications.Clarification;
import com.codecomb.module.clarifications.ClarificationRepository;
import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public class ClarificationRepositoryImpl implements ClarificationRepository {

	@Override
	public List<Clarification> getClarifications(Context context,String accessToken,
			String contestID) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Base responseClarification(Context context,String accessToken, int cardID,
			String answer, int status) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	

}

