package cn.utopay.gblwsdk.payclass.ym;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.payclass.BasePay;

//import com.android.yimeng.ympay.in.BupPayCalBackListener;
//import d.e.f.t.hr.Yent;

/**
 * 小美支付
 */
public class Ym extends BasePay {

	public static final String SDK_NAME = "小美支付";
	public static final String id = "11";
	private boolean isPaySuccess = false;

	@Override
	public void init(Activity activity) {
		super.init(activity);
//		Yent.getInstance(activity).init(activity);
	}

	@Override
	public void pay(final Activity activity, final UniCallback uniCallback) {
		super.pay(activity,uniCallback);
		JSONObject payJson = getPayConfig().getPayParamJson();
		try {
			final int feeId = Integer.parseInt(payJson.getString("feeId"));
			final String money = payJson.getString("money");
//			Yent.getInstance(activity).pay(feeId, "", activity, new BupPayCalBackListener() {
//				@Override
//				public void success(int code) {
////					if(isPaySuccess) {
////						return;
////					}
////					isPaySuccess = true;
//					ReportPaidThread.reportSuccess(activity,SDK_NAME,money,uniCallback);
//				}
//				@Override
//				public void fail(int code) {
//					System.out.println(SDK_NAME + ":failed_" + code);
//				}
//			});
		} catch (JSONException e) {
			e.printStackTrace();
			if(uniCallback != null){
				uniCallback.payFailed(e);
			}
		}
	}
}
