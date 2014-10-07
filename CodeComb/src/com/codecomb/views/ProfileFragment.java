package com.codecomb.views;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codecomb.SettingsManager;
import com.codecomb.events.AppExitEvent;
import com.codecomb.exceptions.AppException;
import com.codecomb.infrastructure.asynctask.AvatarDownloader;
import com.codecomb.infrastructure.asynctask.AvatarDownloader.DownloadListener;
import com.codecomb.infrastructure.asynctask.DataLoader;
import com.codecomb.infrastructure.cache.BitmapCacheManage;
import com.codecomb.module.profile.Profile;
import com.codecomb.module.profile.ProfileManager;
import com.codecomb.ufreedom.R;
import com.codecomb.utils.CodeCombUtil;
import com.codecomb.view.widgets.CircularImageView;

import de.greenrobot.event.EventBus;

public class ProfileFragment extends Fragment implements LoaderCallbacks<Profile> {

	private static final String TAG = ProfileFragment.class.getSimpleName();

	private static final int LOAD_PROFILE = 0;

	private View rootView;
	private TextView vMotto;
	private TextView vProfileRating;
	private TextView vProfileName;
	private CircularImageView vAvatar;
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
		

		
	//	Log.e(TAG, "ProfileFragment -   onCreate");

		
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

					}
				});
	}

	private class LoadContactTask extends AsyncTask<Void, Void, Boolean> {

		Profile profile;
		File file;
		@Override
		protected Boolean doInBackground(Void... params) {

			profile = new Profile();
			try {

				profile = ProfileManager.getInstance().getProfile();

				String filename = SettingsManager.getInstance().getUsername()
						+ ".png";

				file = CodeCombUtil.getAvatarFile(filename);

				URL url = new URL(profile.getAvatarURL());

				Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());

				FileOutputStream outputStream;

				outputStream = new FileOutputStream(file);

				bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
				outputStream.flush();
				outputStream.close();

			} catch (AppException e) {

				e.printStackTrace();

				return false;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {

				vProfileName.setText(profile.getNickname());

				String ratting = getActivity().getResources().getString(
						R.string.lb_profile_rating, profile.getRating());

				vProfileRating.setText(ratting);

				vMotto.setText(profile.getMotto());
				try {

					vAvatar.setImageBitmap(getAvatorBitmap(file
							.getAbsolutePath()));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	private void initThreadCallback() {

		avatartThead.setDownloadListener(new DownloadListener<CircularImageView>() {

			@Override
			public void onAvatarDownloaded(CircularImageView token,
					Bitmap avatar) {
				token.setImageBitmap(avatar);
				
			}

			@Override
			public void cache(String key, Bitmap value) {

				BitmapCacheManage.getInstance().addBitmapToCache(key, value);
				
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

	//	Log.e(TAG, "开始下载我的头像:" + profile.getAvatarURL());

		String key = Integer.toString(profile.getUserId());
		
		Bitmap bitmap = BitmapCacheManage.getInstance().getBitmapFromCache(key);
		
		if (bitmap != null) {
			vAvatar.setImageBitmap(bitmap);
		}else{			
			avatartThead.queryAvatar(vAvatar,key ,profile.getAvatarURL());		
		}
		
		
		
		

	}

	@Override
	public void onLoaderReset(Loader<Profile> profile) {

	}

	private Bitmap getAvatorBitmap(String path) {

		Bitmap bitmap = BitmapFactory.decodeFile(path);
		return bitmap;

	}
	
	public void onEvent(AppExitEvent event){
		
		BitmapCacheManage.getInstance().cleanCache();
	}

}
