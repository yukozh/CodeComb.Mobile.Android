
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:09:53
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.contacts;

import java.util.List;

import android.content.Context;

import com.codecomb.ServerLocator;
import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public class ContactServiceImpl  implements ContactService{

	
	
	private ContactRepository contactRepository;
	
	
	public ContactServiceImpl(){
		
		contactRepository = ServerLocator.getInstance(ContactRepository.class);
				
	}


	@Override
	public List<Contact> getContacts(Context context,String accessToken)
			throws AppException {

		return contactRepository.getContacts(context, accessToken);
	}
	
	

}

