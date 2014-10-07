/**
 * @author UFreedom
 * @since 2014 2014-10-6 上午7:54:02
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.cache;

import java.io.File;

import com.codecomb.MyApplication;
import com.codecomb.SettingsManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

public class BitmapCacheManage {

	private static final String TAG = BitmapCacheManage.class.getSimpleName();
	
	private LruCache<String, Bitmap> memoryCache;

	private DiskLruCache diskLruCache;

	private File cacheDir;
	private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB
	private static Context context;

	static {
		context = MyApplication.getInstance().getApplicationContext();
	}

	public static BitmapCacheManage getInstance() {
		return SingletonCreator.instance;
	}

	private static class SingletonCreator {

		private static BitmapCacheManage instance = new BitmapCacheManage();
	}

	public BitmapCacheManage() {

		int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		memoryCache = new LruCache<String, Bitmap>(maxMemory / 8) {

			@Override
			protected int sizeOf(String key, Bitmap value) {

				return value.getByteCount() / 1024;
			}

		};

		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {

			cacheDir = new File(
					android.os.Environment.getExternalStorageDirectory(),
					SettingsManager.getInstance().getUsername());
		} else {
			cacheDir = context.getCacheDir();
		}

		if (!cacheDir.exists())
			cacheDir.mkdirs();

		diskLruCache = DiskLruCache.openCache(cacheDir, DISK_CACHE_SIZE);

	}

	public void addBitmapToCache(String key, Bitmap value) {

		addBitmapToMemoryCache(key, value);

		if (diskLruCache != null && diskLruCache.get(key) == null) {
			diskLruCache.put(key, value);
		}

	}

	public Bitmap getBitmapFromCache(String key) {

		Bitmap memory = getBitmapFromDiskCache(key);
		Bitmap disk = getBitmapFromDiskCache(key);

		if (memory != null) {
			return memory;
		} else if (disk != null) {
			return disk;
		}

		return null;

	}
	
	

	public Bitmap getBitmapFromDiskCache(String key) {

		if (diskLruCache != null) {
			return diskLruCache.get(key);
		}

		return null;

	}
	
	
	

	public void addBitmapToMemoryCache(String key, Bitmap value) {
		if (getBitmapFromMemoryCahce(key) == null) {
			memoryCache.put(key, value);
		}

	}

	public Bitmap getBitmapFromMemoryCahce(String key) {
		return memoryCache.get(key);
	}
	
	
	
	public void cleanCache(){
		
		cleanMemoryCache();
		cleanDiskCache();
		
	}
	
	public void cleanMemoryCache(){
		
		if (memoryCache != null) {
	//		Log.e(TAG, "清理内存中的缓存");
			memoryCache.evictAll();
			
		}
	
		
	}
	
	public void cleanDiskCache(){
		
		if (diskLruCache != null) {
			
//			Log.e(TAG, "清理磁盘中的缓存");

			diskLruCache.clearCache();
		}
		
	}
	
	

}
