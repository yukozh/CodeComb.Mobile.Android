
/**
 * @author UFreedom
 * @since 2014 下午2:01:07
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.login;

import android.content.Context;

import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;




public interface LoginRepository {
	
	public Auth login(Context context,String username,String password)throws AppException;
	public Base logout(Context context) throws AppException;
	public Base loginByBarcode(Context context,String accessToken,String barcode) throws AppException;
	
}

