package cn.utopay.gblwsdk.payclass.utopay;

import android.app.Activity;

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

	public static final String SDK_NAME = "云贝支付";
	public static final String id = "1";
	private static final int SUCCESS_CODE = 0;
	private boolean isPaySuccess = false;

	@Override
	public void init(Activity activity) {
		super.init(activity);
		Map<String, Object> objectMap = getPayConfig().getInitMap();
		int appId = Integer.parseInt(objectMap.get("appId").toString());
		YQPay.init(activity, appId, Unipay.channel);
	}

	@Override
	public void pay(final Activity activity, final UniCallback uniCallback) {
		super.pay(activity,uniCallback);
		JSONObject payJson = getPayConfig().getPayParamJson();
		try {
			final String money = payJson.get("money").toString();
			final String payId = payJson.get("payId").toString();
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
}
