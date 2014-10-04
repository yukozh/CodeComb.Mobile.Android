
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:27:45
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.clarifications;

import java.util.List;

import android.content.Context;

import com.codecomb.ServerLocator;
import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;
import com.codecomb.module.login.Auth;



public class ClarificationServiceimpl implements ClarificationService {

	
	
	private ClarificationRepository clarificationRepository;
	
	
	public ClarificationServiceimpl(){
		
		clarificationRepository = ServerLocator.getInstance(ClarificationRepository.class);
		
	}


	@Override
	public List<Clarification> getClarifications(Context context,String accessToken,
			int contestID) throws AppException {
		// TODO Auto-generated method stub
		return clarificationRepository.getClarifications(context, accessToken, contestID);
	}


	@Override
	public Base responseClarification(Context context, String accessToken, int cardID,
			String answer, int status) throws AppException {
		// TODO Auto-generated method stub
		return clarificationRepository.responseClarification(context, accessToken, cardID, answer, status);
	}
	
	


}

