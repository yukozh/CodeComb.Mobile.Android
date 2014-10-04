
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:13:53
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
import com.codecomb.module.contacts.Contact;
import com.codecomb.module.contacts.ContactRepository;
import com.codecomb.module.login.Auth;



public class ContactRespository implements ContactRepository {

	@Override
	public List<Contact> getContacts(Context context, String accessToken)
			throws AppException {

		return ApiClient.getContacts(context, accessToken);
	}



}

