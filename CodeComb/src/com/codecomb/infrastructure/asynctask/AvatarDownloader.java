/**
 * @author UFreedom
 * @since 2014 2014-10-4 下午2:32:53
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.asynctask;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.codecomb.utils.NetUtil;

public class AvatarDownloader<Token> extends HandlerThread {

	private static final String TAG = "AvatarDownloader";
	private static final int MESSAGE_DOWNLOAD = 0;

	Handler handler;

	Map<Token, String> requestMap = Collections
			.synchronizedMap(new HashMap<Token, String>());

	Handler responseHandler;
	Listener<Token> listener;

	public interface Listener<Token> {
		public void onAvatarDownloaded(Token token, Bitmap avatar);
	}

	public void setListener(Listener<Token> listener) {
		this.listener = listener;
	}

	// public AvatarDownloader() {
	// super(TAG);
	// }

	public AvatarDownloader(Handler responseHandler) {
		super(TAG);
		this.responseHandler = responseHandler;
	}

	@SuppressLint("HandlerLeak")
	@Override
	protected void onLooperPrepared() {

		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {

				if (msg.what == MESSAGE_DOWNLOAD) {

					@SuppressWarnings("unchecked")
					Token token = (Token) msg.obj;
					Log.e(TAG,
							"Got a reauest from url:" + requestMap.get(token));

					handleRequest(token);
				}

			}

		};
	}

	private void handleRequest(final Token token) {

		try {
			final String url = requestMap.get(token);
			if (url == null)
				    return;

			byte[] bitmapBytes = NetUtil.getUrlBytes(url);

			final Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0,
					bitmapBytes.length);
			

			
			Log.e(TAG, "Bitmap created");
			

			responseHandler.post(new Runnable() {

				@Override
				public void run() {

					if (requestMap.get(token) != url) {
						 return;
					}

					requestMap.remove(token);
					listener.onAvatarDownloaded(token, bitmap);

				}
			});

		} catch (IOException e) {

			Log.e(TAG, "Error downloading image", e);
		}

	}

	public void queryAvatar(Token token, String url) {

		requestMap.put(token, url);

		handler.obtainMessage(MESSAGE_DOWNLOAD,token).sendToTarget();
		
		
		
		Log.e(TAG, "Got an url:" + url);
	}
	
	  public void clearQueue() {
	        handler.removeMessages(MESSAGE_DOWNLOAD);
	        requestMap.clear();
	    }

}
