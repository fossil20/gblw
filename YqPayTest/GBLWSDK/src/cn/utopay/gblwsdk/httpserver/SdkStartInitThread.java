package cn.utopay.gblwsdk.httpserver;

import android.app.Activity;
import android.content.Context;

import java.util.List;
import java.util.Map;

import cn.utopay.gblwsdk.model.PayConfig;
import cn.utopay.gblwsdk.pay.UniPayFactory;
import cn.utopay.gblwsdk.payclass.BasePay;
import cn.utopay.gblwsdk.payclass.damai.Damai;
import cn.utopay.gblwsdk.payclass.ePlusPay.EPlusPay;
import cn.utopay.gblwsdk.payclass.shangan.Shangan;
import cn.utopay.gblwsdk.payclass.utopay.UTOPAY;
import cn.utopay.gblwsdk.payclass.weiyun.Weiyun;
import cn.utopay.gblwsdk.payclass.ym.Ym;
import cn.utopay.gblwsdk.payclass.yufeng.Yufeng;
import cn.utopay.gblwsdk.preference.MyPreference;
import cn.utopay.gblwsdk.utils.JsonHelp;

import static cn.utopay.gblwsdk.utils.InvokeUtil.invokeHttp;


public class SdkStartInitThread extends BaseHttpThread {

	private Context context;

	public SdkStartInitThread(String url, Context c,Map<String, String> maps) {
		super(url, null, maps);
		this.context = c;
	}

	@Override
	public void run() {
		//String value = HttpConnect.doHttpPost(url,getPostParams(maps),0,true);
		String value = invokeHttp(url,getPostParams(maps),0,true);
		BasePay.print(context,"sdkStart == " + value);
		// 获取省份,保存
		Activity t = (Activity) context;
		try {
			Map<String,Object> map = JsonHelp.getMapForJsonObj(value);
            if(map != null && !map.containsKey("message")){
				MyPreference.getInstance(context).saveJson(value);
			}
			initSDK(t, JsonHelp.getMapList(context));
//			if (!TextUtils.isEmpty(value)) {
//				JSONArray jsonArray = new JSONArray(value);
//				if (!JsonHelp.isEmpty(jsonArray)) {
//					MyPreference.getInstance(context).saveJson(value);
//					initSDK(t, JsonHelp.getMapList(context));
//				} else {
//					initSDK(t, JsonHelp.getMapList(context));
//				}
//			} else {
//				initSDK(t, JsonHelp.getMapList(context));
//			}
		} catch (Exception e) {
			initSDK(t, JsonHelp.getMapList(context));
		}
	}

	/**
	 *
	 * @param activity
	 * @param mapList
     */
	private void initSDK(final Activity activity, List<Map<String, Object>> mapList) {
		if (mapList == null || mapList.size() == 0) {
			return;
		}

		int size = mapList.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> root = mapList.get(i);
			String sdkName = (String) root.get("sdkName");
			Map<String, Object> initRoot = (Map<String, Object>) root.get("init");
			PayConfig payConfig = new PayConfig();
			payConfig.setSdkName(sdkName);
			payConfig.setInitMap(initRoot);
			try {
				switch (sdkName) {
					case UTOPAY.SDK_NAME:
						UniPayFactory.getInstance().init(activity,payConfig,UTOPAY.class);
						break;
					case Ym.SDK_NAME:
						//UniPayFactory.getInstance().init(activity,payConfig,Ym.class);
						break;
					case Yufeng.SDK_NAME:
						UniPayFactory.getInstance().init(activity,payConfig,Yufeng.class);
						break;
					case EPlusPay.SDK_NAME:
						UniPayFactory.getInstance().init(activity,payConfig,EPlusPay.class);
						break;
					case Damai.SDK_NAME:
						UniPayFactory.getInstance().init(activity,payConfig,Damai.class);
						break;
					case Weiyun.SDK_NAME:
						UniPayFactory.getInstance().init(activity,payConfig,Weiyun.class);
						break;
					case Shangan.SDK_NAME:
						UniPayFactory.getInstance().init(activity,payConfig,Shangan.class);
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
