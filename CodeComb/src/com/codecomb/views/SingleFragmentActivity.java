
/**
 * @author UFreedom
 * @since 2014 2014-10-1 下午5:53:49
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.codecomb.ufreedom.R;



public abstract class SingleFragmentActivity extends FragmentActivity {

	protected abstract Fragment createFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		
		Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
		
		if (fragment == null) {
			
			fragment = createFragment();
			fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment)
			.commit();
				
		}
	
	}
	
	
	
	
	
}

