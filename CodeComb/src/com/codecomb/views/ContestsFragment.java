/**
 * @author UFreedom
 * @since 2014 2014-10-4 下午10:33:57
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.codecomb.infrastructure.asynctask.DataLoader;
import com.codecomb.module.contests.Contest;
import com.codecomb.module.contests.ContestManager;
import com.codecomb.ufreedom.R;
import com.codecomb.views.adapter.ContestsAdapter;

public class ContestsFragment extends Fragment
		implements
			LoaderCallbacks<List<Contest>> {

	private static final int LOAD_CONTESTS = 0;

	private static final String TAG = ContestsFragment.class.getSimpleName();;

	private View rootView;
	private ListView lvContest;
	private ContestsAdapter contestsAdapter;
	private LoaderManager loaderManager;

	private List<Contest> contests;

	public static ContestsFragment newInstance() {

		return new ContestsFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		contests = new ArrayList<Contest>();
		contestsAdapter = new ContestsAdapter(getActivity(), contests);
	
		
		loaderManager = getLoaderManager();
		loaderManager.initLoader(LOAD_CONTESTS, null, this);

		Log.e(TAG, "ContestsFragment -   onCreate");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fgm_contests, container, false);

		lvContest = (ListView) rootView.findViewById(R.id.lvContests);
		lvContest.setAdapter(contestsAdapter);

	
		lvContest.setEmptyView(rootView.findViewById(R.id.vEmptyView));
		
		

		initListener();

		return rootView;
	}

	private void initListener() {

		lvContest.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Contest contest = (Contest) lvContest
						.getItemAtPosition(position);

				Intent intent = new Intent(getActivity(),
						ClarificationActivity.class);
				intent.putExtra(ClarificationFragment.EXTRA_CONTEST_ID,
						contest.getContestID());
				startActivity(intent);

			}

		});
	}

	private static class ContestsLoader extends DataLoader<List<Contest>> {

		public ContestsLoader(Context context) {
			super(context);

		}

		@Override
		public List<Contest> loadInBackground() {
			return ContestManager.getInstance().getManagedContest(1);
		}

	}

	@Override
	public Loader<List<Contest>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new ContestsLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<Contest>> arg0, List<Contest> list) {

		contests.addAll(list);
		updateUI();
	}

	private void updateUI() {

		contestsAdapter.notifyDataSetChanged();
	}

	@Override
	public void onLoaderReset(Loader<List<Contest>> arg0) {

	}

}
