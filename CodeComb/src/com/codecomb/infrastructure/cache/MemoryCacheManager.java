/**
 * @author UFreedom
 * @since 2014 2014-10-6 上午12:09:24
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.cache;

import java.util.WeakHashMap;

import android.graphics.Bitmap;
import android.util.Log;

public class MemoryCacheManager {

	public static MemoryCacheManager getInstance() {
		return SingletonCreator.instance;
	}

	private static class SingletonCreator {

		private static MemoryCacheManager instance = new MemoryCacheManager();
	}

	private static final String TAG = "MemoryCache";

	// WeakReference Map: key=string, value=Bitmap
	private WeakHashMap<String, Bitmap> cache = new WeakHashMap<String, Bitmap>();

	/**
	 * Search the memory cache by a unique key.
	 * 
	 * @param key
	 *            Should be unique.
	 * @return The Bitmap object in memory cache corresponding to specific key.
	 * */
	public Bitmap get(String key) {
		if (key != null)
			return cache.get(key);
		return null;
	}

	/**
	 * Put a bitmap into cache with a unique key.
	 * 
	 * @param key
	 *            Should be unique.
	 * @param value
	 *            A bitmap.
	 * */
	public void put(String key, Bitmap value) {
		if (key != null && !"".equals(key) && value != null) {
			cache.put(key, value);
			// Log.i(TAG, "cache bitmap: " + key);
			Log.d(TAG, "size of memory cache: " + cache.size());
		}
	}

	/**
	 * clear the memory cache.
	 * */
	public void clear() {
		cache.clear();
	}
}
