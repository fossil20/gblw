package cn.utopay.gblwsdk.utils;

import java.io.UnsupportedEncodingException;

public class HexUtil {
	public static String hexEncode(String src) {
		byte[] srcArr = null;
		try {
			srcArr = src.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// do nothing
		}
		char[] retArr = new char[srcArr.length];
		for (int i = 0; i < srcArr.length; i++) {
			retArr[i] = (char) (srcArr[i] & 0xff);
		}
		return new String(retArr);
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	public static String hexDecode(String src) {
		char[] srcArr = src.toCharArray();
		byte[] retArr = new byte[srcArr.length];
		for (int i = 0; i < srcArr.length; i++) {
			retArr[i] = (byte) srcArr[i];
		}
		try {
			return new String(retArr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// do nothing
		}
		return null;
	}

	public static String str2HexStr(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		sb.append("{ ");
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append("(byte) 0x");
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
			sb.append(", ");
		}
		sb.append("}");
		return sb.toString().trim();
	}

	public static String byte2String(byte[] bs) {
		String str = null;
		try {
			str = new String(bs, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
