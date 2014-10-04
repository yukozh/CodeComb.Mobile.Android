
/**
 * @author UFreedom
 * @since 2014 下午1:52:15
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb;

import java.util.HashMap;
import java.util.Map;

import com.codecomb.module.broadcast.BroadcastRepository;
import com.codecomb.module.broadcast.BroadcastService;
import com.codecomb.module.clarifications.ClarificationRepository;
import com.codecomb.module.clarifications.ClarificationService;
import com.codecomb.module.contacts.ContactRepository;
import com.codecomb.module.contacts.ContactService;
import com.codecomb.module.contests.ContestRepository;
import com.codecomb.module.contests.ContestService;
import com.codecomb.module.im.MessageRepository;
import com.codecomb.module.im.MessageService;
import com.codecomb.module.login.LoginRepository;
import com.codecomb.module.login.LoginService;
import com.codecomb.module.profile.ProfileRepository;
import com.codecomb.module.profile.ProfileService;

public class ServerLocator {
	
	
	
	private static Map<Object, Object> objects;
	
	
	static{
	
		objects = new HashMap<Object, Object>();
		
		init();
		
	}


	
	
	private static void init() {
		
		objects.put(LoginRepository.class, 
				new com.codecomb.infrastructure.repository.LoginRepositoryImpl());
		
		objects.put(ProfileRepository.class, 
				new com.codecomb.infrastructure.repository.ProfileRepositoryImpl());
				
		objects.put(ClarificationRepository.class, 
				new com.codecomb.infrastructure.repository.ClarificationRepositoryImpl());
			
		objects.put(ContestRepository.class, 
				new com.codecomb.infrastructure.repository.ContestRepositoryImpl());
		
		objects.put(MessageRepository.class, 
				new com.codecomb.infrastructure.repository.MessageRepositoryImpl());
		
		objects.put(BroadcastRepository.class, 
				new com.codecomb.infrastructure.repository.BroadcastRepositoryImpl());
		
		
		objects.put(ContactRepository.class, 
				new com.codecomb.infrastructure.repository.ContactRespository());
		
		
		
		
		
		objects.put(LoginService.class, new com.codecomb.module.login.LoginServiceImpl());
		
		objects.put(ProfileService.class, new com.codecomb.module.profile.ProfileServiceImpl());

		objects.put(ClarificationService.class, new com.codecomb.module.clarifications.ClarificationServiceimpl());

		objects.put(ContestService.class, new com.codecomb.module.contests.ContestServiceImpl());

		objects.put(MessageService.class, new com.codecomb.module.im.MessageServiceImpl());

		objects.put(BroadcastService.class, new com.codecomb.module.broadcast.BroadcastServiceImpl());
		
		objects.put(ContactService.class, new com.codecomb.module.contacts.ContactServiceImpl());


	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<?> cls){
		
		return (T) objects.get(cls);
	}
	
	
	

}

