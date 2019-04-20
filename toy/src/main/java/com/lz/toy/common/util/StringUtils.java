package com.lz.toy.common.util;

import java.util.Random;

public class StringUtils {
	
	public static boolean isNULL(String str) {
		return (str == null) || str.trim().equals("");
	}
	
	/**
	 * 获取9位随机数字或者密码
	 * @author Administrator
	 * */
	public static String genCodes(int _length, long _count) {
		StringBuilder val = new StringBuilder();
		for (int j = 0; j < _count; j++) {
			Random random = new Random();
			for (int i = 0; i < _length; i++) {
				String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

				if ("char".equalsIgnoreCase(charOrNum)) // 字符串
				{
					int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
					val.append((char) (choice + random.nextInt(26)));
				} else {
					val.append(String.valueOf(random.nextInt(10)));
				}
			}
			val = new StringBuilder(val.toString().toLowerCase());
		}
		return val.toString();
	}   
	
	/**
	 * 拼装字符串,以特殊符号隔开
	 */
	public static String getStr(String[] strs,String symbol){
		StringBuffer sb = new StringBuffer();
		for(String str : strs){
			sb.append(str).append(symbol);
		}
		return sb.substring(0, sb.length() - 1).toString();
	}
	
	/**
	 * 拼装字符串,以特殊符号隔开
	 */
	public static String getStr(String[] strs){
		return getStr(strs,",");
	}
	
}