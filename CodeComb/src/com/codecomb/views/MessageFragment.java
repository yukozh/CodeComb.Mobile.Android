package com.codecomb.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecomb.ufreedom.R;

public class MessageFragment extends Fragment {

	
	
	
	public static MessageFragment newInstance(){
		return new MessageFragment();
	}
	
	private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	

		rootView = inflater.inflate(R.layout.fgm_message, container, false);
		
		
		
		
		return rootView;
	}

	

}
