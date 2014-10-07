/**
 * @author UFreedom
 * @since 2014 2014-10-7 上午11:16:40
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.codecomb.ufreedom.R;

public class BroadcastDialogFragment extends DialogFragment {

	public static final String EXTRA_BROADCAST = "broadcast";

	private String broadcast;

	public static BroadcastDialogFragment newInstance(String broadcast) {

		Bundle bundle = new Bundle();
		bundle.putString(EXTRA_BROADCAST, broadcast);

		BroadcastDialogFragment fragment = new BroadcastDialogFragment();

		fragment.setArguments(bundle);

		return fragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		broadcast = getArguments().getString(EXTRA_BROADCAST);

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_broadcast, null);

		TextView vBroadcastContent = (TextView) view
				.findViewById(R.id.vBroadcastContent);
		vBroadcastContent.setText(broadcast);

		view.findViewById(R.id.vOkPanel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						dismiss();
					}
				});
		

		return new AlertDialog.Builder(getActivity(),
				AlertDialog.THEME_HOLO_LIGHT).setView(view).create();

	}

}
