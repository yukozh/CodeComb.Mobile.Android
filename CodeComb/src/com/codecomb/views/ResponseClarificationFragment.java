/**
 * @author UFreedom
 * @since 2014 2014-10-2 上午8:42:06
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codecomb.exceptions.AppException;
import com.codecomb.module.base.Base;
import com.codecomb.module.clarifications.Clarification;
import com.codecomb.module.clarifications.ClarificationManager;
import com.codecomb.ufreedom.R;
import com.commonsware.cwac.richedit.RichEditText;

public class ResponseClarificationFragment extends Fragment
		implements
			OnClickListener {

	public static final String EXTRA_CLARFICATION = ResponseClarificationFragment.class
			.getSimpleName();
	private static final int REQUEST_STATUS = 0;

	private Clarification clarification;

	private View rootView;
	private TextView vClarificationQuestion;
	private RichEditText vClarificationAnswer;
	private View vBack;
	private View vSave;
	private TextView vTitle;

	public static Fragment newInstance(Clarification clarification) {

		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_CLARFICATION, clarification);
		ResponseClarificationFragment fragment = new ResponseClarificationFragment();
		fragment.setArguments(bundle);

		return fragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		clarification = (Clarification) getArguments().getSerializable(
				EXTRA_CLARFICATION);

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fgm_response_clarfication,
				container, false);
		initActionBar();

		initWidget();

		return rootView;
	}

	private void initActionBar() {

		vBack = rootView.findViewById(R.id.vBack);
		vTitle = (TextView) rootView.findViewById(R.id.vTitle);
		vSave = rootView.findViewById(R.id.vSave);
		vTitle.setText(clarification.getCategory());

		vBack.setOnClickListener(this);
		vSave.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

			case R.id.vBack :

				getActivity().finish();

				break;

			case R.id.vSave :
				
				
				FragmentManager fragment = getActivity()
						.getSupportFragmentManager();
				ClarificationStatusDialogFragment dialogFragment = new ClarificationStatusDialogFragment();
				dialogFragment.setTargetFragment(
						ResponseClarificationFragment.this, REQUEST_STATUS);
				dialogFragment.show(fragment,
						"ClarificationStatusDialogFragment");

				break;

			default :
				break;
		}

	}

	private void initWidget() {

		vClarificationQuestion = (TextView) rootView
				.findViewById(R.id.vClarificationQuestion);
		vClarificationAnswer = (RichEditText) rootView
				.findViewById(R.id.vClarificationAnswer);

		vClarificationQuestion.setText(clarification.getQuestion());
		vClarificationAnswer.setText(clarification.getAnswer());

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode != Activity.RESULT_OK)
			return;
		if (requestCode == REQUEST_STATUS) {
			int status = data.getIntExtra(
					ClarificationStatusDialogFragment.EXYTRA_RESULT_STATUS, 0);

			new UpdateCalrificationTask().execute(status);
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	class UpdateCalrificationTask extends AsyncTask<Integer, Void, Boolean> {

		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(getActivity(),
					ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setTitle("正在保存");
			progressDialog.setMessage("拼命保存中...");
			progressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (progressDialog.isShowing())
				   progressDialog.dismiss();
			

			if (result) {

				Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT)
						.show();
				getActivity().finish();

			} else {

				Toast.makeText(getActivity(), "保存失败", Toast.LENGTH_SHORT)
						.show();

			}

			super.onPostExecute(result);
		}

		@Override
		protected Boolean doInBackground(Integer... params) {

			try {

				Base base = ClarificationManager.getInstance()
						.responseClarifications(clarification.getClarID(),
								vClarificationAnswer.getText().toString(),
								params[0]);

				return base.isSuccess();

			} catch (AppException e) {

				e.printStackTrace();

				return false;
			}

		}

	}

}
