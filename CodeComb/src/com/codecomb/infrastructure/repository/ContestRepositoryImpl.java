
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:03:47
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.repository;

import java.util.List;

import android.content.Context;

import com.codecomb.infrastructure.respository.wabapi.net.ApiClient;
import com.codecomb.module.contests.Contest;
import com.codecomb.module.contests.ContestRepository;
import com.codecomb.xceptions.AppException;



public class ContestRepositoryImpl implements ContestRepository {

	@Override
	public List<Contest> getContests(Context context, String accessToken)
			throws AppException {
		
		return ApiClient.getContacts(context, accessToken);
	}

	@Override
	public List<Contest> getContests(Context context, String accessToken, int page)
			throws AppException {

		return ApiClient.getContests(context, accessToken, page);
	}

	@Override
	public List<Contest> getManagedContests(Context context, String accessToken, int page)
			throws AppException {

		return ApiClient.getManagerContests(context, accessToken, page);
	}

	@Override
	public List<Contest> getManagedContests(Context context, String accessToken)
			throws AppException {

		return ApiClient.getManagerContests(context, accessToken);
	}



}

