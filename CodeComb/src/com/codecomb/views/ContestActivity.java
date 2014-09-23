package com.codecomb.views;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.codecomb.ufreedom.R;
import com.codecomb.view.notboringcationbar.AlphaForegroundColorSpan;
import com.codecomb.view.notboringcationbar.KenBurnsView;

public class ContestActivity extends Activity {

	private static final String TAG = "com.ufreedom.codecomb.uis.MainActivity";
	private int actionBarTitleColor;
	private int actionBarHeight;
	private int headerHeight;
	private int minHeaderTranslation;
	
	
	private KenBurnsView vHeaderPicture;
	private ImageView vHeaderLogo;
	private ListView lvCompetition;
	private View vHeader;
	private View vPlaceHolderView;
	private AccelerateDecelerateInterpolator smoothInterpolator;

	

	private RectF rect1 = new RectF();
	private RectF rect2 = new RectF();

	private AlphaForegroundColorSpan alphaForegroundColorSpan;
	private SpannableString spannableString;
	private TypedValue typedValue = new TypedValue();
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		
		smoothInterpolator = new AccelerateDecelerateInterpolator();

		headerHeight = getResources().getDimensionPixelSize(
				R.dimen.dim_header_height);
		minHeaderTranslation = -headerHeight + getActionBarHeight();

		
		
		setContentView(R.layout.fgm_contest);

		
		lvCompetition = (ListView) findViewById(R.id.lvCompetition);
		
		
		
		
		vHeader = findViewById(R.id.header);
		vHeaderPicture = (KenBurnsView) findViewById(R.id.header_picture);
		vHeaderPicture.setResourceIds(R.drawable.picture0, R.drawable.picture1);
		vHeaderLogo = (ImageView) findViewById(R.id.header_logo);

		actionBarTitleColor = getResources().getColor(
				R.color.col_actionbar_title_color);

		spannableString = new SpannableString(getString(R.string.app_name));
		alphaForegroundColorSpan = new AlphaForegroundColorSpan(
				actionBarTitleColor);

		
		
		
		
		setupActionBar();
		setupListView();
	}

	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// MenuInflater inflater = getMenuInflater();
	//
	// inflater.inflate(R.menu.search_activity_actions, menu);
	//
	// Toast.makeText(getApplicationContext(), "menu",
	// Toast.LENGTH_SHORT).show();
	// return super.onCreateOptionsMenu(menu);
	// }

	
	
	
	private void setupListView() {
		ArrayList<String> FAKES = new ArrayList<String>();
		for (int i = 0; i < 1000; i++) {
			FAKES.add("entry " + i);
		}
		vPlaceHolderView = getLayoutInflater().inflate(
				R.layout.view_header_placeholder, lvCompetition, false);
		lvCompetition.addHeaderView(vPlaceHolderView);
		lvCompetition.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, FAKES));
		
		
		lvCompetition.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int scrollY = getScrollY();
				// sticky actionbar
				vHeader.setTranslationY(Math.max(-scrollY,
						minHeaderTranslation));
				// header_logo --> actionbar icon
				float ratio = clamp(vHeader.getTranslationY()
						/ minHeaderTranslation, 0.0f, 1.0f);
				interpolate(vHeaderLogo, getActionBarIconView(),
						smoothInterpolator.getInterpolation(ratio));
				// actionbar title alpha
				// getActionBarTitleView().setAlpha(clamp(5.0F * ratio - 4.0F,
				// 0.0F, 1.0F));
				// ---------------------------------
				// better way thanks to @cyrilmottier
				setTitleAlpha(clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F));
			}
		});
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	private void setTitleAlpha(float alpha) {
		alphaForegroundColorSpan.setAlpha(alpha);
		spannableString.setSpan(alphaForegroundColorSpan, 0,
				spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		getActionBar().setTitle(spannableString);
	}

	public static float clamp(float value, float max, float min) {
		return Math.max(Math.min(value, min), max);
	}

	private void interpolate(View view1, View view2, float interpolation) {
		getOnScreenRect(rect1, view1);
		getOnScreenRect(rect2, view2);

		float scaleX = 1.0F + interpolation
				* (rect2.width() / rect1.width() - 1.0F);
		float scaleY = 1.0F + interpolation
				* (rect2.height() / rect1.height() - 1.0F);
		float translationX = 0.5F * (interpolation * (rect2.left
				+ rect2.right - rect1.left - rect1.right));
		float translationY = 0.5F * (interpolation * (rect2.top
				+ rect2.bottom - rect1.top - rect1.bottom));

		view1.setTranslationX(translationX);
		view1.setTranslationY(translationY - vHeader.getTranslationY());
		view1.setScaleX(scaleX);
		view1.setScaleY(scaleY);
	}

	private RectF getOnScreenRect(RectF rect, View view) {
		rect.set(view.getLeft(), view.getTop(), view.getRight(),
				view.getBottom());
		return rect;
	}

	public int getScrollY() {
		View c = lvCompetition.getChildAt(0);
		if (c == null) {
			return 0;
		}

		int firstVisiblePosition = lvCompetition.getFirstVisiblePosition();
		int top = c.getTop();

		int headerHeight = 0;
		if (firstVisiblePosition >= 1) {
			headerHeight = vPlaceHolderView.getHeight();
		}

		return -top + firstVisiblePosition * c.getHeight() + headerHeight;
	}

	private void setupActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setIcon(R.drawable.ic_transparent);

		// getActionBarTitleView().setAlpha(0f);
	}

	private ImageView getActionBarIconView() {
		return (ImageView) findViewById(android.R.id.home);
	}

	/*
	 * private TextView getActionBarTitleView() { int id =
	 * Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
	 * return (TextView) findViewById(id); }
	 */

	public int getActionBarHeight() {
		if (actionBarHeight != 0) {
			return actionBarHeight;
		}
		getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue,
				true);
		actionBarHeight = TypedValue.complexToDimensionPixelSize(
				typedValue.data, getResources().getDisplayMetrics());
		return actionBarHeight;
	}
}
