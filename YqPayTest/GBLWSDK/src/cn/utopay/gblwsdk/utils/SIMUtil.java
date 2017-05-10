package cn.utopay.gblwsdk.utils;

import java.lang.reflect.Method;

import android.content.Context;
import android.telephony.TelephonyManager;

public class SIMUtil {

	private static String getMainCardIMSI(Context context) {
		// LogUtil.v("enter get MainCardIMSI");
		if (context == null) {
			LogUtil.v("MainCardIMSI err context = null");
			return null;
		}
		String IMSI = null;
		try {
			boolean isDualMode = DualModeUtil.isDualMode();
			if (isDualMode) {
				int index = DualModeUtil.getMainCardIndex(context);
				if (index != -1)
					IMSI = DualModeUtil.getSubscriberId(index);
			} else {
				IMSI = DualModeUtil.getSubscriberId(0);
				if ((IMSI == null) || (IMSI.length() == 0))
					IMSI = DualModeUtil.getSubscriberId(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (IMSI == null) {
			IMSI = PhoneUtil.getIMSI(context);
		}
		return IMSI;
	}
	private static String imsi="";
	public static String getIMSI2(Context context) {
		if(SIMUtil.imsi.length() == 15)
			return SIMUtil.imsi;
		String imsi = getMainCardIMSI(context);
		if (!TextUtil.notNull(imsi)) {
			imsi = getNewIMSI(context);
		}
		if(imsi != null && imsi.length() == 15) 
			SIMUtil.imsi = imsi;
		return imsi;
	}

	private static String getNewIMSI(Context context) {
		String imsi = null;
		try {
			TelephonyManager mTelephonyManager = null;
			mTelephonyManager = ((TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE));
			imsi = mTelephonyManager.getSubscriberId();
			if (!TextUtil.notNull(imsi)) {
				Class<? extends TelephonyManager> tmClass = mTelephonyManager
						.getClass();

				Method getImsiMethod = tmClass.getMethod(
						"getSubscriberIdGemini", Integer.TYPE);

				if (null != getImsiMethod) {
					// 先取SIM2
					imsi = (String) getImsiMethod.invoke(mTelephonyManager, 1);
					if (null == imsi) {
						imsi = (String) getImsiMethod.invoke(mTelephonyManager,
								0);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error e) {

		}
		if (!TextUtil.notNull(imsi)) {
			// 高通平台
			try {
				String c1 = "android.telephony.MSimT";
				String c2 = "elephonyManager";
				Class clazz = Class
						.forName(c1 + c2);
				Object obj = context.getSystemService("phone_msim");
				c1 = "getSubs";
				c2 = "criberId";
				Method md = clazz.getMethod(c1 + c2, int.class);
				imsi = (String) md.invoke(obj, 1);
				if (!TextUtil.notNull(imsi)) {
					imsi = (String) md.invoke(obj, 0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return imsi;
	}

}
