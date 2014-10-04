package com.codecomb.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codecomb.module.broadcast.BroadcastManager;
import com.codecomb.ufreedom.R;
import com.commonsware.cwac.richedit.RichEditText;
import com.dd.processbutton.iml.SubmitProcessButton;

public class BroadcastFragment extends Fragment {

	private static String TAG = BroadcastFragment.class.getSimpleName();

	private View rootView;
	private SubmitProcessButton vPushBroadcast;
	private RichEditText vBroadcastMessage;

	public static BroadcastFragment newInstance() {
		return new BroadcastFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		initActionBar();

		super.onCreate(savedInstanceState);
	}

	private void initActionBar() {

		// ActionBar actionBar = getActivity().getActionBar();
		// if (actionBar != null) {
		// actionBar.setTitle(R.string.lb_broadcast);
		//
		// }
		//

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fgm_broadcast, container, false);

		vPushBroadcast = (SubmitProcessButton) rootView
				.findViewById(R.id.vPushBroadcast);
		vBroadcastMessage = (RichEditText) rootView
				.findViewById(R.id.vBroadcastMessage);

		initListener();

		return rootView;
	}

	private void initListener() {

		vPushBroadcast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new AsyncTask<Void, Integer, Boolean>() {

					@Override
					protected void onPreExecute() {
						// TODO Auto-generated method stub
						super.onPreExecute();
					}

					@Override
					protected void onProgressUpdate(Integer... values) {

						vPushBroadcast.setProgress(values[0]);

						super.onProgressUpdate(values);
					}

					@Override
					protected Boolean doInBackground(Void... params) {

						String message = vBroadcastMessage.getText().toString()
								.trim();

						return BroadcastManager.getInstance().sendBroadcast(
								message);
					}

					@Override
					protected void onPostExecute(Boolean result) {

						if (result) {

							Toast.makeText(getActivity(), "广播发送成功",
									Toast.LENGTH_SHORT).show();
							vBroadcastMessage.setText("");
						}else {
							Toast.makeText(getActivity(), "广播发送失败",
									Toast.LENGTH_SHORT).show();
						}

						super.onPostExecute(result);
					}

				}.execute();

			}
		});

	}

}
