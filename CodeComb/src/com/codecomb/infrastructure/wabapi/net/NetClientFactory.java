
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午3:27:04
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.wabapi.net;



public class NetClientFactory {
	
	public static NetClient getClient(){
		return new HttpNetClient();
	}

}

