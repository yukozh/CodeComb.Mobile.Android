package com.codecomb.views;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.barcode.CaptureActivity;
import com.codecomb.SettingsManager;
import com.codecomb.events.AppExitEvent;
import com.codecomb.exceptions.AppException;
import com.codecomb.infrastructure.asynctask.AvatarDownloader;
import com.codecomb.infrastructure.asynctask.AvatarDownloader.DownloadListener;
import com.codecomb.infrastructure.asynctask.DataLoader;
import com.codecomb.infrastructure.cache.BitmapCacheManage;
import com.codecomb.module.base.Base;
import com.codecomb.module.login.AuthManager;
import com.codecomb.module.profile.Profile;
import com.codecomb.module.profile.ProfileManager;
import com.codecomb.ufreedom.R;
import com.codecomb.utils.CodeCombUtil;
import com.codecomb.view.widgets.CircularImageView;

import de.greenrobot.event.EventBus;

public class ProfileFragment extends Fragment
		implements
			LoaderCallbacks<Profile> {

	private static final String TAG = ProfileFragment.class.getSimpleName();

	private static final int LOAD_PROFILE = 0;

	private static final int REQUEST_CODE = 0;
	private static final int REQ_CODE_BARCODE = 1;

	private View rootView;
	private TextView vMotto;
	private TextView vProfileRating;
	private TextView vProfileName;
	private CircularImageView vAvatar;

	private PopupWindow progressPopupWindow;
	private TextView vProgressInfo;
	private View vProgressPanel;
	private View vCanclePanel;
	private View vOkPanel;
	private TextView vOkInfo;
	private View vWornPanel;
	private TextView vWornInfo;

	// private View vScanPanel;

	private Profile profile;

	private LoaderManager loaderManager;
	private AvatarDownloader<CircularImageView> avatartThead;

	public static ProfileFragment newInstance() {
		return new ProfileFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		EventBus.getDefault().register(this);

		// Log.e(TAG, "ProfileFragment -   onCreate");
		initProgressPopupWindow();

		loaderManager = getLoaderManager();

		loaderManager.initLoader(LOAD_PROFILE, null, this);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fgm_about_me, container, false);

		initWidget();

		// new LoadContactTask().execute();

		avatartThead = new AvatarDownloader<CircularImageView>(new Handler());

		initThreadCallback();

		avatartThead.start();
		avatartThead.getLooper();

		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();

	}

	private void initWidget() {

		vMotto = (TextView) rootView.findViewById(R.id.vMotto);
		vProfileRating = (TextView) rootView.findViewById(R.id.vProfileRating);
		vProfileName = (TextView) rootView.findViewById(R.id.vProfileName);
		vAvatar = (CircularImageView) rootView.findViewById(R.id.vAvatar);

		vAvatar.setImageResource(R.drawable.ic_avatar_default);
		rootView.findViewById(R.id.vScanPanel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent intent = new Intent(getActivity(),
								CaptureActivity.class);

						startActivityForResult(intent, REQ_CODE_BARCODE);

					}
				});
	}

	private void initProgressPopupWindow() {

		if (progressPopupWindow == null) {

			View contentView = LayoutInflater.from(getActivity()).inflate(
					R.layout.popup_window_common, null);

			progressPopupWindow = new PopupWindow(contentView,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			vProgressPanel = contentView.findViewById(R.id.vProgressPanel);
			vProgressInfo = (TextView) contentView
					.findViewById(R.id.vProgressInfo);
			vCanclePanel = contentView.findViewById(R.id.vCanclePanel);
			vOkPanel = contentView.findViewById(R.id.vOKPanel);
			vOkInfo = (TextView) contentView.findViewById(R.id.vOkInfo);

			vWornPanel = contentView.findViewById(R.id.vWornPanel);
			vWornInfo = (TextView) contentView.findViewById(R.id.vWornInfo);
		}

	}

	private void showOkPopupWindow(View parent) {

		if (progressPopupWindow != null) {

			vProgressPanel.setVisibility(View.GONE);
			vWornPanel.setVisibility(View.GONE);

			vOkPanel.setVisibility(View.VISIBLE);
			vOkInfo.setText("Web端已成功登陆");
//			progressPopupWindow.update(parent, LayoutParams.WRAP_CONTENT,
//					LayoutParams.WRAP_CONTENT);
			progressPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
			

		}
	}

	private void showWornPopupWindow(View parent) {

		if (progressPopupWindow != null) {

			vProgressPanel.setVisibility(View.GONE);
			vOkPanel.setVisibility(View.GONE);

			vWornPanel.setVisibility(View.VISIBLE);
			vWornInfo.setText("登陆出错");
			
			progressPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
			


		}
	}

	private void showPopupWindow(View parent) {

		if (progressPopupWindow != null) {
			progressPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
		}

	}

	private void showCanclePopupWindow() {

	}

	private void hidePopupWindow() {
		if (progressPopupWindow != null) {
			progressPopupWindow.dismiss();
		}
	}

	private void initThreadCallback() {

		avatartThead
				.setDownloadListener(new DownloadListener<CircularImageView>() {

					@Override
					public void onAvatarDownloaded(CircularImageView token,
							Bitmap avatar) {
						token.setImageBitmap(avatar);

					}

					@Override
					public void cache(String key, Bitmap value) {

						BitmapCacheManage.getInstance().addBitmapToCache(key,
								value);

					}
				});

	}

	private static class ProfileLoader extends DataLoader<Profile> {

		public ProfileLoader(Context context) {
			super(context);
		}

		@Override
		public Profile loadInBackground() {

			return ProfileManager.getInstance().getProfile();
		}

	}

	@Override
	public Loader<Profile> onCreateLoader(int arg0, Bundle arg1) {

		return new ProfileLoader(getActivity());

	}

	@Override
	public void onLoadFinished(Loader<Profile> arg0, Profile profile) {

		this.profile = profile;

		vProfileName.setText(profile.getNickname());
		vMotto.setText(profile.getMotto());

		String ratting = getActivity().getResources().getString(
				R.string.lb_profile_rating, profile.getRating());

		vProfileRating.setText(ratting);

		// Log.e(TAG, "开始下载我的头像:" + profile.getAvatarURL());

		String key = Integer.toString(profile.getUserId());

		Bitmap bitmap = BitmapCacheManage.getInstance().getBitmapFromCache(key);

		if (bitmap != null) {
			vAvatar.setImageBitmap(bitmap);
		} else {
			avatartThead.queryAvatar(vAvatar, key, profile.getAvatarURL());
		}

	}

	@Override
	public void onLoaderReset(Loader<Profile> profile) {

	}

	private Bitmap getAvatorBitmap(String path) {

		Bitmap bitmap = BitmapFactory.decodeFile(path);
		return bitmap;

	}

	public void onEvent(AppExitEvent event) {

		BitmapCacheManage.getInstance().cleanCache();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {

			case REQ_CODE_BARCODE : {
				if (resultCode == Activity.RESULT_OK) {

					final String code = data.getStringExtra("RESULT");

					// Toast.makeText(getActivity(), "二维码:" + code,
					// Toast.LENGTH_SHORT).show();
					//

					new AsyncTask<Void, Void, Boolean>() {

						
						
						
						@Override
						protected void onPreExecute() {
							super.onPreExecute();
							vProgressInfo.setText("正在进行Web端登陆");
							showPopupWindow(rootView);

						}

						@Override
						protected Boolean doInBackground(Void... params) {

							try {

								Base base = AuthManager.getInstance()
										.loginByBarcode(code);

								if (base.isSuccess()) {
									return true;
								}

							} catch (AppException e) {
								e.printStackTrace();
								return false;
							}

							return false;

						}

						@Override
						protected void onPostExecute(Boolean result) {
			
							if (result) {					
								showOkPopupWindow(rootView);	
							}else{
								showWornPopupWindow(rootView);
							}

							
							new Handler().postDelayed(new Runnable() {
								
								@Override
								public void run() {
									hidePopupWindow();		
								}
							}, 1000);

						}

					}.execute();

				}
				break;
			}
			default :
				break;
		}
	}

}
