package com.randioo.config.randioo_excel.util;

public class StringUtils {
	/**
	 * 字符串第一个字符大写
	 * 
	 * @param str
	 * @return
	 * @author wcy 2016年12月16日
	 */
	public static String firstStrToUpperCase(String str) {
		if (str == null || str.equals(""))
			return str;
		String firstStr = str.substring(0, 1);
		return str.replaceFirst(firstStr, firstStr.toUpperCase());
	}
}
