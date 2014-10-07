/**
 * @author UFreedom
 * @since 2014 2014-10-5 下午11:58:35
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.utils;

import java.io.File;

import com.codecomb.MyApplication;

public class CodeCombUtil {

	public static File getAvatarFile(String fileName) throws Exception {

		File imageDir = getAvatarDir();

		return new File(imageDir, fileName);

	}

	public static File getAvatarDir() throws Exception {

		File moduleDir = getModuleDir("Profile");

		File audioDir = new File(moduleDir, "Avatar");
		if (!audioDir.exists()) {
			audioDir.mkdirs();
		}

		return audioDir;

	}

	public static File getModuleDir(String module) throws Exception {
		File userDir = getCurrentUserDir();

		File moduleDir = new File(userDir, module);
		if (!moduleDir.exists()) {
			moduleDir.mkdirs();
		}
		return moduleDir;
	}
	
	public static File getCurrentUserDir() throws Exception{
		return Utils.getCurrentUserDir(MyApplication.getInstance()
				.getApplicationContext());
	}
	
}
