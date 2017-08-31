package com.szahch.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * 加密算法PBKDF2 Password-Based Key Derivation Function
 * 
 * @author Alex
 * @version 0.1
 */

// TODO 有bug
public class PBKDF2 {

	/**
	 * 加密算法
	 * 
	 * @param password
	 *            需要加密的密码
	 * @param salt
	 *            盐值
	 * @param count
	 *            迭代的次数
	 * @param dkLen
	 *            输出加密后密码的长度
	 * @return 加密后的密码
	 */
	public static String encrypt(String password, String salt, int iterations, int dkLen) {

		String encryption = null;

		KeySpec spec = new PBEKeySpec(password.toCharArray(), fromHex(salt), iterations, dkLen);

		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			encryption = toHex(f.generateSecret(spec).getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return encryption;

	}

	/**
	 * 十六进制字符串转二进制字符串
	 * 
	 * @param hex
	 *            the hex string
	 * @return the hex string decoded into a byte array
	 */
	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			
			
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;

	}

	/**
	 * 二进制字符串转十六进制字符串
	 * 
	 * @param array
	 *            the byte array to convert
	 * @return a length*2 character string encoding the byte array
	 */
	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}
}
