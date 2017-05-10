package cn.utopay.gblwsdk.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * class name：TestNetWork<BR>
 * class description：获取网络信息类<BR>
 * PS： <BR>
 * 
 * @version 1.00 2011/09/21
 * @author CODYY)peijiangping
 */
public class NetWorkUtil {

	public static boolean hasNetWork(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}
		return true;
	}

	/**
	 * 作者: peijiangping<BR>
	 * 时间:2012-12-21下午6:22:38<BR>
	 * 功能:获取可用网络列表<BR>
	 * 返回值:void<BR>
	 */
	public static List<String> getNetWorkList(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] infos = cm.getAllNetworkInfo();
		List<String> list = new ArrayList<String>();
		if (infos != null) {
			for (int i = 0; i < infos.length; i++) {
				NetworkInfo info = infos[i];
				String name = null;
				if (info.getTypeName().equals("WIFI")) {
					name = info.getTypeName();
				} else {
					name = info.getExtraInfo();
				}
				if (name != null && list.contains(name) == false) {
					list.add(name);
				}
			}
		}
		return list;
	}

	public static String getNetWork(Context c) {
		String NOWNET = null;
		ConnectivityManager cm = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			if (info.getTypeName().equals("WIFI")) {
				NOWNET = info.getTypeName();
			} else {
				NOWNET = info.getExtraInfo();// cmwap/cmnet/wifi/uniwap/uninet
			}
		}
		return NOWNET;
	}

	public static void OpenAndCloseWIFI(Context c, boolean go) {
		WifiManager wifiManager = (WifiManager) c
				.getSystemService(Context.WIFI_SERVICE);
		wifiManager.setWifiEnabled(go);
	}

	public static void openGprs(Context c, boolean enabled) {
		ConnectivityManager cm = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		Class<?> cmClass = cm.getClass();
		Class<?>[] argClasses = new Class[1];
		argClasses[0] = boolean.class;
		// 反射ConnectivityManager中hide的方法setMobileDataEnabled，可以开启和关闭GPRS网络
		Method method = null;
		try {
			method = cmClass.getMethod("setMobileDataEnabled", argClasses);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			method.invoke(cm, enabled);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
