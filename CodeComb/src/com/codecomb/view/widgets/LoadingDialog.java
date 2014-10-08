
/**
 * @author UFreedom
 * @since 2014 2014-10-8 下午10:12:37
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.view.widgets;

import com.codecomb.ufreedom.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;



public class LoadingDialog extends Dialog {

	
	Context context ;
	
	public static final int  ACTION_LOADING = 0;
	public static final int ACTION_CANCLE = 1;
	public static final int ACTION_WORIND = 2;
	
	private String info;
	
	public LoadingDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		
		this.setContentView(R.layout.popup_window_common);
	}
	
	
	
	
	
	

}

