package com.codecomb.views;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.codecomb.ufreedom.R;

public class MainActivity extends FragmentActivity implements
		View.OnClickListener {

	private ViewPager viewPager;
	private Button vContest;
	private View vAboutMe;
	private View vMessage;
	private View vBroadcast;

	private MyPagerAdapter pagerAdapter;

	private static final int ACTIVITY_ITEM_CONTEST = 0;
	private static final int ACTIVITY_ITEM_BROADCAST = 1;
	private static final int ACTIVITY_ITEM_MESSAGE = 2;
	private static final int ACTIVITY_ITEM_ABOUT_ME = 3;
	protected static final String TAG = "com.ufreedom.codecomb.uis.MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		initWidget();

		initViewPagerListener();

		updateBottomNavigateBtnState(ACTIVITY_ITEM_CONTEST);


	}

	private void initWidget() {

		viewPager = (ViewPager) findViewById(R.id.viewPager);

		pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);

		vAboutMe = findViewById(R.id.vAboutMe);
		vAboutMe.setOnClickListener(this);
		vBroadcast = findViewById(R.id.vBroadcast);
		vBroadcast.setOnClickListener(this);

		vContest = (Button) findViewById(R.id.vContest);
		vContest.setOnClickListener(this);

		vMessage = findViewById(R.id.vMessage);
		vMessage.setOnClickListener(this);

	}

	private void initViewPagerListener() {

		viewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {

						updateBottomNavigateBtnState(position);
					}

				});

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.vContest:

			viewPager.setCurrentItem(ACTIVITY_ITEM_CONTEST);

			break;

		case R.id.vBroadcast:

			viewPager.setCurrentItem(ACTIVITY_ITEM_BROADCAST);

			break;

		case R.id.vMessage:
			viewPager.setCurrentItem(ACTIVITY_ITEM_MESSAGE);

			break;

		case R.id.vAboutMe:
			viewPager.setCurrentItem(ACTIVITY_ITEM_ABOUT_ME);

			break;

		default:

			break;
		}

	}

	private void updateBottomNavigateBtnState(int which) {

		switch (which) {
		case ACTIVITY_ITEM_CONTEST:

			vContest.setSelected(true);
			vBroadcast.setSelected(false);
			vMessage.setSelected(false);
			vAboutMe.setSelected(false);

			break;

		case ACTIVITY_ITEM_BROADCAST:

			vBroadcast.setSelected(true);

			vContest.setSelected(false);
			vMessage.setSelected(false);
			vAboutMe.setSelected(false);
			break;

		case ACTIVITY_ITEM_MESSAGE:

			vMessage.setSelected(true);

			vContest.setSelected(false);
			vBroadcast.setSelected(false);
			vAboutMe.setSelected(false);

			break;

		case ACTIVITY_ITEM_ABOUT_ME:

			vAboutMe.setSelected(true);

			vContest.setSelected(false);
			vBroadcast.setSelected(false);
			vMessage.setSelected(false);

			break;

		default:
			break;
		}

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.home, menu);
//		return true;
//	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		List<Fragment> fragments = new ArrayList<Fragment>();

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			fragments.add(ContestsFragment.newInstance());
			fragments.add(BroadcastFragment.newInstance());
			fragments.add(MessageFragment.newInstance());
			fragments.add(ProfileFragment.newInstance());
		}

		@Override
		public Fragment getItem(int postion) {
			return fragments.get(postion);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

	}

}
