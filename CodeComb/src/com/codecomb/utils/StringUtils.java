package com.codecomb.utils;

import java.util.Collection;
import java.util.regex.Pattern;

public class StringUtils {

	public static String paddingLeft(String srcStr, String paddingChars, int count)
	{
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < count; i++)
		{
			result.append(paddingChars);
		}
		
		result.append(srcStr);
		
		
		return result.toString();
		
	}
	
	
	public static String paddingRight(String srcStr, String paddingChars, int count)
	{
		StringBuilder result = new StringBuilder(srcStr);
		
		for(int i = 0; i < count; i++)
		{
			result.append(paddingChars);
		}
		
		
		return result.toString();
		
	}
	
	public static String clearWhitespace(String str)
	{
	    Pattern p =	Pattern.compile("\\s");
	    return p.matcher(str).replaceAll("");
	    
		
	}
	
	public static String getNumber(String str)
	{
		Pattern p =	Pattern.compile("/d+");
		return p.matcher(str).group(1);
	}
	
	public static String[] getStringArray(String str){
		return str.split(",");
	}
	
	public static String getJoinString(Collection<String> strs)
	{

		StringBuilder sb = new StringBuilder();
		
		for (String str : strs)
		{
			sb.append(str + ",");
		}
		if (sb.length() > 0)
		{
			return sb.substring(0, sb.length() - 1);
		}
		
		return "";
	}


}
