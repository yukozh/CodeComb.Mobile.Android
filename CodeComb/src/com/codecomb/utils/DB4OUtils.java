package com.codecomb.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.codecomb.MyApplication;

public class DB4OUtils {

	private static final String FILE_NAME = "db_icld_fga.yap";

	public static String getDataBasePath() {

		return MyApplication.getInstance().getApplicationContext().getFilesDir() + File.separator
				+ FILE_NAME;

	}

	public static <T> void copyFields(T srcObj, T decObj) {

		Field[] fields = srcObj.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if (!Modifier.isStatic(f.getModifiers())) {
				f.setAccessible(true);

				try {
					f.set(decObj, f.get(srcObj));
				} catch (IllegalArgumentException e) {

					e.printStackTrace();

				} catch (IllegalAccessException e) {
					e.printStackTrace();

				}

			}

		}

	}

	public interface Match<T> {

		public boolean match(T oldObj, T newObj);

	}

}
