package com.szahch.test.database;

import com.szahch.utils.PBKDF2;

public class PBKDF2Test {
	public static void main(String[] args) {
		String str = PBKDF2.encrypt("root", "aaaa", 1000, 128);
		System.out.println(str);
		System.out.println(str.equals("575c172b80a7feeefaf986704f4a5c30"));
	}
}
