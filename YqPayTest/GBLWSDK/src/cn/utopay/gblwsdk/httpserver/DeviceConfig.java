package cn.utopay.gblwsdk.httpserver;

import android.content.Context;

import java.util.Map;

import cn.utopay.gblwsdk.config.ConFigFile;
import cn.utopay.gblwsdk.utils.MyHashMap;
import cn.utopay.gblwsdk.utils.PhoneUtil;
import cn.utopay.gblwsdk.utils.SIMUtil;

public class DeviceConfig {

	public static Map<String, String> getUserBaseDeviceInfo(Context c,Integer appId,String ch) {
		MyHashMap<String, String> maps = new MyHashMap<String, String>();
		maps.put("appId", String.valueOf(appId));
		maps.put("imei", PhoneUtil.getIMEI(c));
		maps.put("iccid", PhoneUtil.getICCID(c));
		maps.put("imsi", SIMUtil.getIMSI2(c));
		maps.put("packageName", c.getPackageName());
		maps.put("sdktag", ConFigFile.sdkTag);
		maps.put("placeCode", ch);
		maps.put("onlyThird", "false");
//		maps.put("imei", "862620029972272");
//		maps.put("manufacturer", PhoneUtil.getMobileManufacturer());
//		maps.put("iccid", "89860076184802544605");
//		maps.put("imsi", "460002542644605");
//		maps.put("osVersion", PhoneUtil.getAndroidVersion());
		return maps;
	}

	
}
