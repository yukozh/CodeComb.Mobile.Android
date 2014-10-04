
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:23:38
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.clarifications;

import java.util.List;

import android.content.Context;

import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;
import com.codecomb.module.login.Auth;



public interface ClarificationService {

	
	public List<Clarification> getClarifications(Context context,String accessToken,int contestID) throws AppException;
	
	public Base responseClarification(Context context,String accessToken,int cardID,String answer,int status) throws AppException;
	
	
}

