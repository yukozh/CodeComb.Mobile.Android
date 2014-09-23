package com.codecomb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String convertTime(String time) {
		return time;
	}

	public static Date convert(String strDate) {
		return convert(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date convert(String strDate, String pattern) {
		Date result = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			result = format.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static String getCurDate(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	public static String getCurDate() {
		return getCurDate("yyyy-MM-dd HH:mm:ss");
	}

	public static String toString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	public static String getWebServiceDateString(Date date) {
		return format(date, "yyyy-MM-dd'T'HH:mm:ss'Z'");

	}
	
	public static Date getWebServiceDate(String strDate)
	{
		return convert(strDate, "yyyy-MM-dd'T'HH:mm:ss'Z'");
	}
	
	

}
