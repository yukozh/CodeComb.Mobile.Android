package com.dd.processbutton.util;

import java.util.Random;

import android.os.Handler;

import com.dd.processbutton.ProcessButton;

public class ProgressGenerator {

	public interface OnCompleteListener {

		public void onTaskComplete();

		public void onTaskCancle();

		public void onTaskStop();

	}

	private OnCompleteListener mListener;
	private int mProgress;
	private static boolean  isContinue = true;

	public ProgressGenerator(OnCompleteListener listener) {
		mListener = listener;
	}

	public void start(final ProcessButton button) {
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
//				if(isContinue){
//					mProgress += 1;
//					button.setProgress(mProgress);				
//					handler.postDelayed(this, generateDelay());	
//				}
				
				mProgress += 1;
				button.setProgress(mProgress);
				handler.post(this);
		//		handler.postDelayed(this, generateDelay());	
			}
		}, generateDelay());
	}

	public void stop(){
		mProgress = 0;
		isContinue = false;
		mListener.onTaskStop();
	}
	public void complete() {

		mListener.onTaskComplete();
	}

	public void cancle() {

		mListener.onTaskCancle();
	}

	private Random random = new Random();

	private int generateDelay() {
		return random.nextInt(1000);
	}
}
