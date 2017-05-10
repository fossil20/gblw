package cn.utopay.gblwsdk.utils;

import java.lang.reflect.Method;

import android.content.Context;
import android.os.IBinder;
import android.telephony.TelephonyManager;

public class DualModeUtil {

	public static String getMainCardIMSI(Context context) {
		LogUtil.v("enter get MainCardIMSI");
		if (context == null) {
			LogUtil.v("MainCardIMSI err context = null");
			return null;
		}
		String IMSI = null;
		try {
			boolean isDualMode = isDualMode();
			if (isDualMode) {
				int index = getMainCardIndex(context);
				if (index != -1)
					IMSI = getSubscriberId(index);
			} else {
				IMSI = getSubscriberId(0);
				if ((IMSI == null) || (IMSI.length() == 0))
					IMSI = getSubscriberId(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (IMSI == null) {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService("phone");
			IMSI = telephonyManager.getSubscriberId();
		}
		return IMSI;
	}

	public static String getSubscriberId(int cardIndex) {
		String name = null;
		name = cardIndex == 1 ? "iphonesubinfo2" : "iphonesubinfo";
		try {
			String c1 = "android.os.";
			String c2 = "ServiceManager";
			Method method = Class.forName(c1+c2)
					.getDeclaredMethod("getService",
							new Class[] { String.class });
			method.setAccessible(true);
			Object param = method.invoke(null, new Object[] { name });
			if ((param == null) && (cardIndex == 1))
				param = method.invoke(null, new Object[] { "iphonesubinfo1" });
			if (param == null)
				return null;
			c1 = "com.android.internal";
			c2 = ".telephony.IPhoneSubInfo$Stub";
			method = Class.forName(
					c1 + c2)
					.getDeclaredMethod("asInterface",
							new Class[] { IBinder.class });
			method.setAccessible(true);
			Object stubObj = method.invoke(null, new Object[] { param });
			return (String) stubObj.getClass()
					.getMethod("getSubscriberId", new Class[0])
					.invoke(stubObj, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getMainCardIndex(Context context) {
		int index = 0;
		index = getMTK(context);
		LogUtil.v("getMTK index = " + index);
		if (index == -1) {
			index = getSPR(context);
			LogUtil.v("getSPR index = " + index);
			if (index == -1) {
				index = getGaotong();
				LogUtil.v("getGaotong index = " + index);
			}
		}
		if ((index == 0) || (index == 1)) {
			return index;
		}
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService("phone");
		String IMSI = telephonyManager.getSubscriberId();
		LogUtil.v("enter getMainCardIndex IMSI = " + IMSI);
		if ((IMSI != null) && (IMSI.equals(getSubscriberId(0))))
			return 0;
		if ((IMSI != null) && (IMSI.equals(getSubscriberId(1)))) {
			return 1;
		}
		return -1;
	}

	private static int getMTK(Context context) {
		try {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService("phone");
			Method method = Class.forName("android.telephony.TelephonyManager")
					.getDeclaredMethod("getSmsDefaultSim", new Class[0]);
			method.setAccessible(true);
			Object index = method.invoke(tm, new Object[0]);
			return ((Integer) index).intValue();
		} catch (Exception e) {
			LogUtil.v("getMTK Exception = " + e);
		}
		return -1;
	}

	private static int getSPR(Context context) {
		try {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService("phone");
			Method method = Class.forName("android.telephony.TelephonyManager")
					.getDeclaredMethod("getDefaultDataPhoneId", new Class[0]);
			method.setAccessible(true);
			Object index = method.invoke(tm, new Object[0]);
			return ((Integer) index).intValue();
		} catch (Exception e) {
			LogUtil.v("getSPR Exception = " + e);
		}
		return -1;
	}

	private static int getGaotong() {
		byte[] Byte_SmsManage = { (byte) 0x61, (byte) 0x6E,
				(byte) 0x64, (byte) 0x72, (byte) 0x6F, (byte) 0x69, (byte) 0x64,
				(byte) 0x2E, (byte) 0x74, (byte) 0x65, (byte) 0x6C, (byte) 0x65,
				(byte) 0x70, (byte) 0x68, (byte) 0x6F, (byte) 0x6E, (byte) 0x79,
				(byte) 0x2E, (byte) 0x53, (byte) 0x6D, (byte) 0x73, (byte) 0x4D,
				(byte) 0x61, (byte) 0x6E, (byte) 0x61, (byte) 0x67, (byte) 0x65,
				(byte) 0x72 };// android.telephony.SmsManager
		try {
			Method method = Class.forName(HexUtil.byte2String(Byte_SmsManage))
					.getDeclaredMethod("getDefault", new Class[0]);
			method.setAccessible(true);
			Object param = method.invoke(null, new Object[0]);
			method = Class.forName(HexUtil.byte2String(Byte_SmsManage))
					.getDeclaredMethod("getPreferredSmsSubscription",
							new Class[0]);
			method.setAccessible(true);
			Object index = method.invoke(param, new Object[0]);
			return ((Integer) index).intValue();
		} catch (Exception e) {
			LogUtil.v("getGaotong Exception = " + e);
		}
		return -1;
	}

	public static boolean isDualMode() {
		String imsi1 = getSubscriberId(0);
		String imsi2 = getSubscriberId(1);
//		LogUtil.v("enter isDualMode imsi1 = " + imsi1 + " imsi2 = " + imsi2);
		if ((imsi1 != null) && (imsi2 != null)) {
			return true;
		}
		return false;
	}
}
