package com.jimaio.vanir.utils;

public class Utils {
	
	public static String generateRandomNumber(int noOfDigits) {
		StringBuilder sb = new StringBuilder();
		while (sb.length() < noOfDigits) {
			int random = (int) (Math.random() * 10);
			if (random == 0 && sb.length() == 0)
				continue;
			sb.append(random);
		}
		
		return sb.toString();
	}
	
}
