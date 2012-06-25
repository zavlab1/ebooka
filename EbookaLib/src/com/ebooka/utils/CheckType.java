package com.ebooka.utils;

public class CheckType {
	public static boolean isDigit(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e){
			return false;
		}
	}
}
