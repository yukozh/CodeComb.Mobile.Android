
/**
 * @author UFreedom
 * @since 2014 2014-10-1 下午5:27:30
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import android.support.v4.app.Fragment;



public class ClarificationActivity extends SingleFragmentActivity {



	private static final String TAG = ClarificationActivity.class.getSimpleName();
	
	@Override
	protected Fragment createFragment() {
				
		int contestID = getIntent().getIntExtra(ClarificationFragment.EXTRA_CONTEST_ID,0);
		
		
		
		return ClarificationFragment.newInstance(contestID);
		
	}

	

	
	
}

