package com.codecomb.views;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codecomb.SettingsManager;
import com.codecomb.module.profile.Profile;
import com.codecomb.module.profile.ProfileManager;
import com.codecomb.ufreedom.R;
import com.codecomb.view.widgets.CircularImageView;
import com.codecomb.xceptions.AppException;

public class ProfileFragment extends Fragment {

	private View rootView;
	private TextView vMotto;
	private TextView vProfileRating;
	private TextView vProfileName;
	private CircularImageView vAvatar;


	public static ProfileFragment newInstance() {
		return new ProfileFragment();
	}
	
	
	
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fgm_about_me, container, false);

		initWidget();
		new LoadContactTask().execute();
		
		
	
		
		return rootView;
	}






	private void initWidget() {

		 vMotto = (TextView) rootView.findViewById(R.id.vMotto);
		 vProfileRating = (TextView) rootView.findViewById(R.id.vProfileRating);
		 vProfileName = (TextView) rootView.findViewById(R.id.vProfileName);
		 vAvatar = (CircularImageView) rootView.findViewById(R.id.vAvatar);
	
	}
	
	
	private class LoadContactTask extends AsyncTask<Void, Void, Boolean>{

		Profile profile ;
		File file;
		@Override
		protected Boolean doInBackground(Void... params) {
		
			profile = new Profile();
			try {
				
				
				profile = ProfileManager.getInstance().getProfile();
			
			
				String filename  = SettingsManager.getInstance().getUsername() + ".png";
				
				file = ProfileManager.getInstance().getMottoFile(filename);
				
				URL url = new URL(profile.getAvatarURL());
				
				Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
				
				
				FileOutputStream outputStream;
				
				outputStream= new FileOutputStream(file);
				
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
				
				String ratting = getActivity().getResources().getString(R.string.lb_profile_rating, profile.getRating());
				
				vProfileRating.setText(ratting);
			
				vMotto.setText(profile.getMotto());
				try {
					
					
					vAvatar.setImageBitmap(getAvatorBitmap(file.getAbsolutePath()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			
			
			
			
			
		}
		
		
	
	}
	
	private Bitmap getAvatorBitmap(String  path){
		
		
		Bitmap bitmap = BitmapFactory.decodeFile(path);
					
		return bitmap;
		
	}
	
	
	
	

}
