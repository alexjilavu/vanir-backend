package com.jimaio.vanir.utils;

public class Utils {
	
	public static String generateRandomNumber(int noOfDigits) {
		StringBuilder sb = new StringBuilder();
		boolean addSpacing = false;
		if (noOfDigits > 4)
			addSpacing = true;
		
		int digitsAdded = 0;
		while (digitsAdded < noOfDigits) {
			int random = (int) (Math.random() * 10);
			if (random == 0 && sb.length() == 0)
				continue;
			
			if (addSpacing && (digitsAdded % 4) == 0 && digitsAdded != 0)
				sb.append(" ");
			
			sb.append(random);
			digitsAdded++;
		}
		
		return sb.toString();
	}
	
}
