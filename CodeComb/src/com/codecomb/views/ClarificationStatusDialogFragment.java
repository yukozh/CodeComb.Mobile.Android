/**
 * @author UFreedom
 * @since 2014 2014-10-2 上午10:37:12
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;

import com.codecomb.ufreedom.R;

public class ClarificationStatusDialogFragment extends DialogFragment
		implements
			OnClickListener {
	
	public static final String EXYTRA_RESULT_STATUS = "result";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_clarification_status, null);

		view.findViewById(R.id.vStatusBroadcastPanel).setOnClickListener(this);

		view.findViewById(R.id.vStatusPrivatePanel).setOnClickListener(this);
		view.findViewById(R.id.vStatusCanclePanel).setOnClickListener(this);

		return new AlertDialog.Builder(getActivity(),
				AlertDialog.THEME_HOLO_LIGHT).setView(view).create();

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			
			case R.id.vStatusBroadcastPanel :
				
				sendResult(Activity.RESULT_OK, 2);

				dismiss();
				break;
			case R.id.vStatusPrivatePanel :

				sendResult(Activity.RESULT_OK, 1);

				dismiss();
				break;
				
			case R.id.vStatusCanclePanel :

				dismiss();
				break;

			default :
				break;
		}

	}
	
	
	private void sendResult(int resultCode,int status){
		
		if (getTargetFragment() == null) {
			return;
		}
		
		Intent intent = new Intent();
		intent.putExtra(EXYTRA_RESULT_STATUS, status);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
		
	}

}
