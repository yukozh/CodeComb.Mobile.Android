
/**
 * @author UFreedom
 * @since 2014 2014-10-2 上午8:40:21
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import com.codecomb.module.clarifications.Clarification;

import android.support.v4.app.Fragment;



public class ResponseClarificationActivity extends SingleFragmentActivity {


	@Override
	protected Fragment createFragment() {

		Clarification clarification = (Clarification) getIntent().
				getSerializableExtra(ResponseClarificationFragment.EXTRA_CLARFICATION);
		
		
		return ResponseClarificationFragment.newInstance(clarification);
	}

}

