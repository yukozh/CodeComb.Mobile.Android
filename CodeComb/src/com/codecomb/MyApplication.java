/**
 * @author UFreedom
 * @since 2014 上午12:20:24
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import android.app.Application;
import android.os.Handler;

import com.codecomb.events.InitializedEvent;

import de.greenrobot.event.EventBus;




public class MyApplication extends Application {

	private static MyApplication mInstance;
	private Handler mHandler;
	private ExecutorService mBackgroundExecutor;

	private boolean mInitialized;
	
	private boolean mClosing;
	private boolean mClosed;
	private boolean mLoaded;
	private boolean mStartService;

	private final Runnable mTimerRunnable = new Runnable() {

		@Override
		public void run() {

			// EventBus.getDefault().post(new TimerEvent());

			if (!mClosing)
				startTimer();
		}

	};

	private static final int TIMER_DELAY = 1 * 60 * 1000;

	public MyApplication() {
		mInstance = this;

		mClosed = false;
		mInitialized = false;
		mStartService = false;

		mHandler = new Handler();
		mBackgroundExecutor = Executors
				.newCachedThreadPool(new ThreadFactory() {

					@Override
					public Thread newThread(Runnable r) {
						// TODO Auto-generated method stub
						Thread thread = new Thread(r,
								"Background executor service.");
						thread.setPriority(Thread.NORM_PRIORITY);
						thread.setDaemon(true);
						return thread;
					}
				});

	}

	public static MyApplication getInstance() {
		return mInstance;
	}

	public boolean isInitialized() {
		return mInitialized;
	}

	public boolean isLoaded() {
		return mLoaded;
	}

	private void startTimer() {
		// TODO Auto-generated method stub
		runOnUIThreadDelayed(mTimerRunnable, TIMER_DELAY);
	}

	public Handler getHandler() {
		return mHandler;
	}

	private void onInitialized() {

		EventBus.getDefault().post(new InitializedEvent());
		mInitialized = true;
		startTimer();

	}

	private void onClose() {
	//	EventBus.getDefault().post(new CloseEvent());
		mClosed = true;
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public void onServiceStarted() {
		if (mStartService) {
			return;
		}

		mStartService = true;

		onInitialized();

	}

	public void onServiceDestroy() {
		if (mClosed)
			return;
		onClose();

	}

	public void runOnUIThread(Runnable runnable) {
		mHandler.post(runnable);
	}

	public void runOnBackground(Runnable runnable) {
		mBackgroundExecutor.submit(runnable);

	}

	public void runOnUIThreadDelayed(Runnable runnable, int delay) {
		mHandler.postDelayed(runnable, delay);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		// initCompnents();
		
		 EventBus.getDefault().post(new InitializedEvent());

		

		super.onCreate();
	}

	

}