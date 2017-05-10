package cn.utopay.gblwsdk.payclass.weiyun;

import android.app.Activity;

import com.wyzf.constant.PayResult;
import com.wyzf.pay.PayResultListener;
import com.wyzf.pay.WYZFPay;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.utopay.gblwsdk.httpserver.ReportPaidThread;
import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.pay.Unipay;
import cn.utopay.gblwsdk.payclass.BasePay;

/**
 * 微云支付
 */
public class Weiyun extends BasePay {

	public static final String SDK_NAME = "微云";
	public static final String id = "15";
	private boolean isPaySuccess = false;

	public void init(final Activity activity) {
		super.init(activity);
		Map<String,Object> initMap = getPayConfig().getInitMap();
		if(initMap != null){
			String appCode = initMap.get("appCode").toString();
			WYZFPay.getInstance().init(activity,appCode, Unipay.channel);
		}
	}

	@Override
	public void pay(final Activity activity, final UniCallback uniCallback) {
		super.pay(activity, uniCallback);
		JSONObject payJson = getPayConfig().getPayParamJson();
		if(payJson != null){
			try {
				final String feeCode = payJson.getString("feeCode");
				final String money = payJson.getString("money");
				int m = Integer.parseInt(money) / 100;
				WYZFPay.getInstance().pay(activity, Integer.parseInt(feeCode),m,new PayResultListener(){

					@Override
					public void onResult(PayResult payResult, int feeCode){
						switch(payResult){
							case SUCCESS:
//								if(isPaySuccess) {
//									return;
//								}
//								isPaySuccess = true;
								String m = String.valueOf(Integer.parseInt(money)/100);
								ReportPaidThread.reportSuccess(activity,SDK_NAME, m, uniCallback);
								break;
							default:
								break;
						}
					}
				});
			} catch (JSONException e) {
				e.printStackTrace();
				if(uniCallback != null){
					uniCallback.payFailed(e);
				}
			}
		}
	}
}
