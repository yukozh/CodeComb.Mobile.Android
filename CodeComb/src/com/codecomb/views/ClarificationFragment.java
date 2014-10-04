
/**
 * @author UFreedom
 * @since 2014 2014-10-1 下午5:32:02
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.codecomb.Constants;
import com.codecomb.module.clarifications.Clarification;
import com.codecomb.module.clarifications.ClarificationManager;
import com.codecomb.ufreedom.R;
import com.codecomb.views.adapter.ClarificationAdapter;

public class ClarificationFragment extends Fragment {

	private static final String TAG =  ClarificationFragment.class.getSimpleName();
	public static final String EXTRA_CONTEST_ID = "contest_id";
	private int contestID;
	
	private View rootView;
	private ListView lvClarification;
	private View vBack;
	
	
	
	
	private ClarificationAdapter clarificationAdapter;
	private List<Clarification> clarifications;
	
	
	
	public static ClarificationFragment newInstance(int contestID){
		
		Bundle bundle = new Bundle();
		bundle.putInt(EXTRA_CONTEST_ID, contestID);
		
		ClarificationFragment fragment = new ClarificationFragment();
		
		fragment.setArguments(bundle);
		
		return fragment;
				
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		contestID = getArguments().getInt(EXTRA_CONTEST_ID);
		
		Toast.makeText(getActivity(), "Contest Id :" + contestID,
				Toast.LENGTH_SHORT).show();
		
	}
	
	
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

	
		rootView  = inflater.inflate(R.layout.fgm_clarification, container, false);

		rootView.findViewById(R.id.vBack).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				getActivity().finish();
				
				
			}
		});
		
		initListView();
		initListViewListener();
		
		
		return rootView;
	}


	
	
	private void initListViewListener() {

		lvClarification.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Clarification clarification = (Clarification) lvClarification.getItemAtPosition(position);

				Intent intent = new Intent(getActivity(),ResponseClarificationActivity.class);
				intent.putExtra(ResponseClarificationFragment.EXTRA_CLARFICATION, clarification);
				startActivity(intent);
			
			}
		});
	}


	private void initListView() {
		lvClarification = (ListView) rootView.findViewById(R.id.lvClarification);
		clarifications = new ArrayList<Clarification>();
		clarificationAdapter = new ClarificationAdapter(getActivity(), clarifications);
		lvClarification.setAdapter(clarificationAdapter);
		
		
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {

				

				List<Clarification> clarifications = ClarificationManager.getInstance().getClarifications(contestID);
				
			
				for (Clarification clarification : clarifications) {
					Log.e(TAG, "Clarification:"+clarification.toString());
				}
				
				
				
				clarificationAdapter = new ClarificationAdapter(getActivity(), clarifications);
	
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				
			
				lvClarification.setAdapter(clarificationAdapter);

				
				super.onPostExecute(result);
			}
			
			
		}.execute();
		
		
	}
	
	
	
	

}

