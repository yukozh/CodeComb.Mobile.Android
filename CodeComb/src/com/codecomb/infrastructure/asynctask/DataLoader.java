
/**
 * @author UFreedom
 * @since 2014 2014-10-3 上午9:08:52
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.asynctask;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;



public abstract class DataLoader<D> extends AsyncTaskLoader<D> {

	
	

	private D data;
	

	public DataLoader(Context context) {
		super(context);


	}


	@Override
	public void deliverResult(D data) {


		this.data = data;
		if (isStarted()) {
			super.deliverResult(data);
		}
	
	}


	@Override
	protected void onStartLoading() {

		
		if (data != null) {
			deliverResult(data);
		}else{
			forceLoad();
		}


	}
	

	
}

