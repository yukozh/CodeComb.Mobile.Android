
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午3:23:06
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.respository.wabapi.net;

import java.util.List;
import java.util.Map;

import android.content.Context;

import com.codecomb.xceptions.AppException;



public interface NetClient {
	
	public String doGet(Context context ,String url,Map<String,Object> params) throws AppException;

	public String doPost(Context context,String url,Map<String,Object> params) throws AppException;


	public String doPostMultipart(Context context,String url,Map<String,Object> params,
			List<String> files) throws AppException;

}

