package cn.utopay.gblwsdk.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;


/**
 * 作者: peijiangping<BR>
 * 2012-12-17下午2:50:50<BR>
 * PhoneUtil.java<BR>
 */
public class PhoneUtil {
	/**
	 * 作者: peijiangping<BR>
	 * 时间:2012-12-17下午2:51:12<BR>
	 * 功能:打电话<BR>
	 * 返回值:void<BR>
	 */
	public static void call(String phoneNum, Context c) {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ phoneNum));
		c.startActivity(intent);
	}

	/**
	 * 作者: peijiangping<BR>
	 * 时间:2012-12-17下午2:49:39<BR>
	 * 功能:发送短信<BR>
	 * 返回值:void<BR>
	 */
	public static void sendMessage(String phoneNum, String content, Context c) {
		Uri smsToUri = Uri.parse("smsto:" + phoneNum);
		Intent mIntent = new Intent(android.content.Intent.ACTION_SENDTO,
				smsToUri);
		mIntent.putExtra("sms_body", content);
		c.startActivity(mIntent);
	}

	public static String getICCID(Context c) {
		TelephonyManager tm = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSimSerialNumber(); // 取出MSISDN，很可能为空
	}

	// /**
	// * 作者: peijiangping<BR>
	// * 时间:2012-12-17下午2:49:49<BR>
	// * 功能:获取电话号码<BR>
	// * 返回值:String<BR>
	// */
	// public static String getNativePhoneNumber(Context c) {
	// TelephonyManager telephonyManager = (TelephonyManager) c
	// .getSystemService(Context.TELEPHONY_SERVICE);
	// String phoneNum = telephonyManager.getLine1Number();
	// if (TextUtil.notNull(phoneNum)) {
	// if (phoneNum.length() == 11 || phoneNum.length() == 14) {
	//
	// } else {
	// phoneNum = "";
	// }
	// }
	// return phoneNum;
	// }

	// 获取手机型号
	public static String getDeviceType() {
		return android.os.Build.MODEL;
	}
	
	public static String getDeviceBrand(){
		return android.os.Build.BRAND;
	}

	// 获取手机品牌
	public static final String getMobileManufacturer() {
		return android.os.Build.MANUFACTURER;
	}

	/**
	 * 是否为模拟器
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isEmulator(Context context) {
		Process process = null;
		String abi = null;
		InputStreamReader ir = null;
		BufferedReader input = null;
		try {
			process = Runtime.getRuntime().exec("getprop ro.product.cpu.abi");
			ir = new InputStreamReader(process.getInputStream());
			input = new BufferedReader(ir);
			abi = input.readLine();
			if (abi.contains("x86")) {
				return true;
			}
		} catch (Exception e) {
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
				}
			}

			if (ir != null) {
				try {
					ir.close();
				} catch (Exception e) {
				}
			}

			if (process != null) {
				try {
					process.destroy();
				} catch (Exception e) {
				}
			}
		}
		return false;
	}

	/**
	 * 作者: peijiangping<BR>
	 * 时间:2012-12-17下午2:55:31<BR>
	 * 功能:获取运营商信息<BR>
	 * 返回值:int<BR>
	 */

	public static String getMacAddress(Context c) {
		WifiManager wm = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
		return wm.getConnectionInfo().getMacAddress();
	}

	public static String getAndroidVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	public static String getIMSI(Context c) {
		TelephonyManager telephonyManager = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSubscriberId();
	}

	public static String getIMEI(Context c) {
		TelephonyManager telephonyManager = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	public static String getDeviceId(Context c) {
		return Secure.getString(c.getContentResolver(), Secure.ANDROID_ID);
	}




}
