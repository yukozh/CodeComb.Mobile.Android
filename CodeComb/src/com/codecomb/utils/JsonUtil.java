/**
 * @author UFreedom
 * @since 2014 2014-10-7 下午12:03:41
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.utils;

import org.json.JSONArray;
import org.json.JSONException;

public class JsonUtil {

	public static String convertToString(JSONArray jsonArray) {

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < jsonArray.length(); i++) {

			try {

				builder.append(jsonArray.getString(i));

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		return builder.toString().trim();

	}

}
