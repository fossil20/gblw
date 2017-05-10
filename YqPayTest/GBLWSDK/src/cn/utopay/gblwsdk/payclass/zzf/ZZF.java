package cn.utopay.gblwsdk.payclass.zzf;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.pay.Unipay;
import cn.utopay.gblwsdk.payclass.BasePay;
import inject.zzf.P;


public class ZZF extends BasePay{

	public static final String SDK_NAME= "掌支付";
	public static final String id = "8";
	private boolean isPaySuccess = false;

	@Override
	public void init(Activity activity) {
		super.init(activity);
		Map<String,Object> initMap = getPayConfig().getInitMap();
		String ch = initMap.get("ch").toString();
		String aid = initMap.get("aid").toString();
		String qd = initMap.get("qd").toString();
		//ZhangPaySdk.getInstance().init(activity, ch, aid, qd);
	}

	@Override
	public void pay(final Activity activity, final UniCallback uniCallback) {
		super.pay(activity, uniCallback);
		JSONObject payJson = getPayConfig().getPayParamJson();
		Map<String,Object> initMap = getPayConfig().getInitMap();
		final HashMap<String,String> payMap = new HashMap<String, String>();
		try {
			payMap.put("channelId",initMap.get("channelId").toString());
			payMap.put("key", (String) initMap.get("key"));
			payMap.put("priciePointId", payJson.getString("priciePointId"));
			payMap.put("priciePointName", payJson.getString("priciePointName"));
			payMap.put("priciePointDec","仅需X.XX元,即可拥有!");
			payMap.put("money",payJson.getString("money"));
			payMap.put("cpparam", Unipay.channel);
			payMap.put("appId", (String) initMap.get("appId"));
			P.realName = (String) initMap.get("appName");
			payMap.put("appName",P.realName);
			payMap.put("appVersion","1000");
			payMap.put("qd",(String) initMap.get("qd"));

			final String mm = Integer.valueOf(payJson.getString("money"))/100 + "";
//			ZhangPaySdk.getInstance().pay(activity, payMap, new ZhangPayCallback() {
//				@Override
//				public void onZhangPayBuyProductOK(String a, String c) {
//					if (c.equals("1001")) {
//						if (isPaySuccess) {
//							return;
//						}
//						isPaySuccess = true;
//						ReportPaidThread.reportSuccess(SDK_NAME, mm, uniCallback);
//					}
//				}
//
//				@Override
//				public void onZhangPayBuyProductFaild(String a, String b) {
//					LogUtil.v(SDK_NAME + ":" + b);
//					System.out.println(SDK_NAME + ":" + b);
//				}
//			}, false);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
