package com.codecomb.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.codecomb.ufreedom.R;
import com.dd.processbutton.iml.SubmitProcessButton;

public class BroadcastFragment extends Fragment {

	
	private View rootView;
	private SubmitProcessButton vPushBroadcast;
	
	
	
	
	public static BroadcastFragment newInstance(){
		return new BroadcastFragment();
	}
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		initActionBar();
		
		super.onCreate(savedInstanceState);
	}




	private void initActionBar() {
		
//		ActionBar actionBar = getActivity().getActionBar();
//		if (actionBar != null) {
//			actionBar.setTitle(R.string.lb_broadcast);
//			
//		}
//		
		
	}




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		rootView = inflater.inflate(R.layout.fgm_broadcast, container, false);
		
		vPushBroadcast = (SubmitProcessButton) rootView.findViewById(R.id.vPushBroadcast);
		
		
		
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

						
						
						
						
						
						
						
						return null;
					}
					
					
				};
				
				
			}
		});
		
		
		
		
	}

	

}
