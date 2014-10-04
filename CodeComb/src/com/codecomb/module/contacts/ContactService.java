
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:12:57
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.contacts;

import java.util.List;

import android.content.Context;

import com.codecomb.exceptions.AppException;
import com.codecomb.module.login.Auth;



public interface ContactService {


	public List<Contact> getContacts(Context context,String accessToken) throws AppException;
	
	
}

