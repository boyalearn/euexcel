package com.euexcel.util;

public class StringUtil {
	public static String upFist(String str){
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

    public static boolean isEmpty(String fileName) {
		return null == fileName || fileName.isEmpty();
    }
}
