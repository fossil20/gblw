package cn.utopay.gblwsdk.utils;

import android.util.Base64;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
//import org.apache.commons.codec.binary.Base64;

public class DesUtil {
	private static byte[] DESIV = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90,
			(byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
	private static final String DES_KEY = "ZFTLLLLL";// 这个key的长度必须是8的倍数
	private static final String BM = "UTF-8";
	private static DESKeySpec keySpec = null;// 设置密钥参数
	private static AlgorithmParameterSpec iv = new IvParameterSpec(DESIV);// 设置向量
	private static SecretKeyFactory keyFactory = null; 
	private static Key key = null;
	static {
		try {
			keySpec = new DESKeySpec(DES_KEY.getBytes(BM));
			keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
			key = keyFactory.generateSecret(keySpec);// 得到密钥对象
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 加密String 明文输入密文输出
	 * 
	 * @param inputString
	 *            待加密的明文
	 * @return 加密后的字符串
	 */
	public static String getEnc(String inputString) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String outputString = null;
		try {
			byteMing = inputString.getBytes(BM);
			byteMi = getEncCode(byteMing);
			//byte[] temp = Base64.encodeBase64(byteMi);
			byte[] temp = Base64.encode(byteMi, Base64.DEFAULT);
			outputString = new String(temp, BM);
		} catch (Exception e) {
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return outputString;
	}

	/**
	 * 解密String 以密文输入明文输出
	 * 
	 * @param inputString
	 *            需要解密的字符串
	 * @return 解密后的字符串
	 */
	public static String getDec(String inputString) {
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = null;
		try {
			//byteMi = Base64.decodeBase64(inputString);
			byteMi = Base64.decode(inputString, Base64.DEFAULT);
			byteMing = getDesCode(byteMi);
			strMing = new String(byteMing, BM);
		} catch (Exception e) {
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param bt
	 *            待加密的字节码
	 * @return 加密后的字节码
	 */
	private static byte[] getEncCode(byte[] bt) {
		byte[] byteFina = null;
		Cipher cipher;
		try {

			// 得到Cipher实例
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byteFina = cipher.doFinal(bt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param bt
	 *            待解密的字节码
	 * @return 解密后的字节码
	 */
	private static byte[] getDesCode(byte[] bt) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			// 得到Cipher实例
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byteFina = cipher.doFinal(bt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}
	
	public static void main(String[] args) {
		String a = DesUtil.getDec("4v16Kzd0VFqSX4I6/6DJ0g==");
		System.out.println(a);
	}
	
}
