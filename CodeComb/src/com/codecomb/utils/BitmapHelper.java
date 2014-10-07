/**
 * @author UFreedom
 * @since 2014 2014-10-6 上午12:11:52
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

public class BitmapHelper {
	public static boolean saveBitmap(File file, Bitmap bitmap) {
		if (file == null || bitmap == null)
			return false;
		try {
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(file));
			return bitmap.compress(CompressFormat.JPEG, 100, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
