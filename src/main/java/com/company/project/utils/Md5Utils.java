package com.company.project.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

	public static String getMd5(String msg){
		MessageDigest md5 = null;
		msg = "abcdefghjklqwertyiopzxcvbnm";
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md5.digest(msg.getBytes());

			StringBuffer hexValue = new StringBuffer();
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
					hexValue.append("0");
				}
                hexValue.append(Integer.toHexString(val));
            }
			return hexValue.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return msg;
		}
	}

	public static String getToken(String msg){
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md5.digest(msg.getBytes());

			StringBuffer hexValue = new StringBuffer();
			for (byte md5Byte : md5Bytes) {
				int val = ((int) md5Byte) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return msg;
		}
	}

	/**
	 * 参数Md5加密
	 *
	 * @param inStr
	 * @return
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		byte[] md5Bytes = md5.digest(inStr.getBytes());

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	//public static void main(String[] args){

		//9ed685f18acbc96ecc280be14afdeee7
		//System.out.println(Md5Utils.getMd5("12345678"));
	//}
}
