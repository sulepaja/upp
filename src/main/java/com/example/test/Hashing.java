package com.example.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
	
	public static String hash(String clearText) {
		String hashedString = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(clearText.getBytes());
			byte[] bytesOfHashedString= md.digest();
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytesOfHashedString.length ;i++)
			{
				sb.append(Integer.toString((bytesOfHashedString[i] & 0xff) + 0x100, 16).substring(1));
			}
			hashedString = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			
			hashedString = null;
		}
		
		return hashedString;
	}
	
}
