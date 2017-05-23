package cn.utopay.gblwsdk.payclass.utopay;

import android.app.Activity;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.utopay.gblwsdk.httpserver.ReportPaidThread;
import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.pay.Unipay;
import cn.utopay.gblwsdk.payclass.BasePay;
import cn.utopay.sdk.interfaces.PCallback;
import cn.utopay.sdk.pay.YQPay;

/**
 * 云贝支付
 */
public class UTOPAY extends BasePay {

	public static final String SDK_CODE = "4e918d1d652f4ed8";

	public static final String SDK_NAME = "云贝支付";
	public static final String id = "1";
	private static final int SUCCESS_CODE = 0;
	private boolean isPaySuccess = false;

	@Override
	public void init(Activity activity) {
		super.init(activity);
		Map<String, Object> objectMap = getPayConfig().getInitMap();
		int appId = Integer.parseInt(objectMap.get("appId").toString());
		if(!TextUtils.isEmpty(initParams(new String[]{String.valueOf(appId),Unipay.channel}))){
			print(activity,initParams(new String[]{String.valueOf(appId),Unipay.channel}));
		}
		YQPay.init(activity, appId, Unipay.channel);
	}

	@Override
	public void pay(final Activity activity, final UniCallback uniCallback) {
		super.pay(activity,uniCallback);
		JSONObject payJson = getPayConfig().getPayParamJson();
		try {
			final String money = payJson.get("money").toString();
			final String payId = payJson.get("payId").toString();
			if(!TextUtils.isEmpty(payParams(new String[]{money,payId}))){
				print(activity,payParams(new String[]{money,payId}));
			}
			YQPay.pay(activity, new PCallback() {
				@Override
				public void payEnd(int code) {
					System.out.println(SDK_NAME + ":" + code);
					if (code == SUCCESS_CODE) {
//						if(isPaySuccess) {
//							return;
//						}
//						isPaySuccess = true;
						String m = String.valueOf(Integer.parseInt(money)/100);
						ReportPaidThread.reportSuccess(activity,SDK_NAME, m, uniCallback);
					}
				}
			}, payId, "");
		} catch (JSONException e) {
			e.printStackTrace();
			if(uniCallback != null){
				uniCallback.payFailed(e);
			}
		}
	}

	@Override
	public String initParams(String...args) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(SDK_NAME + "初始化参数");
		stringBuilder.append(":");
		stringBuilder.append("appId=");
		stringBuilder.append(args[0]);
		stringBuilder.append(",channel=");
		stringBuilder.append(args[1]);
		return stringBuilder.toString();
	}

	@Override
	public String payParams(String... args) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(SDK_NAME + "支付参数");
		stringBuilder.append(":");
		stringBuilder.append("money=");
		stringBuilder.append(args[0]);
		stringBuilder.append(",payId=");
		stringBuilder.append(args[1]);
		return stringBuilder.toString();
	}
}
