
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午4:35:11
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.profile;

import android.content.Context;

import com.codecomb.ServerLocator;
import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public class ProfileServiceImpl implements ProfileService {

	
	
	private ProfileRepository profileRepository; 
	
	
	public ProfileServiceImpl(){
		
		profileRepository = ServerLocator.getInstance(ProfileRepository.class);
		
		
	}
	
	
	@Override
	public Profile getProfile(Context context, String acccessToken) throws AppException {

		return profileRepository.getProfile(context, acccessToken);
		
	}
	
	
	
	

}

