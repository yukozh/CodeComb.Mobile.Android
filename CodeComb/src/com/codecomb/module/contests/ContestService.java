/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午4:55:52
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.contests;

import java.util.List;

import android.content.Context;

import com.codecomb.exceptions.AppException;

public interface ContestService {

	public List<Contest> getContests(Context contect, String accessToken)
			throws AppException;

	public List<Contest> getContests(Context context, String accessToken,
			int page) throws AppException;

	public List<Contest> getManagedContests(Context context,
			String accessToken, int page) throws AppException;

	public List<Contest> getManagedContests(Context context, String accessToken)
			throws AppException;



	public void saveManagedContest(Context context, int userID, Contest contest)
			throws AppException;

	public void saveManagedContests(Context context, int userID,
			List<Contest> cts) throws AppException;

	public List<Contest> getManagedContestsFromLocal(Context context,
			int userID, int page) throws AppException;

}
