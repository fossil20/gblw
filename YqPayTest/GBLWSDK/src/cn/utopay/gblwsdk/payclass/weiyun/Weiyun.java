package cn.utopay.gblwsdk.payclass.weiyun;

import android.app.Activity;
import android.text.TextUtils;

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

	public static final String SDK_CODE = "5fae4e91";
	public static final String SDK_NAME = "微云";
	public static final String id = "15";
	private boolean isPaySuccess = false;

	public void init(final Activity activity) {
		super.init(activity);
		Map<String,Object> initMap = getPayConfig().getInitMap();
		if(initMap != null){
			String appCode = initMap.get("appCode").toString();
			if(!TextUtils.isEmpty(initParams(new String[]{appCode,Unipay.channel}))){
				print(activity,initParams(new String[]{appCode,Unipay.channel}));
			}
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
				if(!TextUtils.isEmpty(payParams(new String[]{feeCode,money}))){
					print(activity,payParams(new String[]{feeCode,money}));
				}
				WYZFPay.getInstance().pay(activity, Integer.parseInt(feeCode),new PayResultListener(){

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

	@Override
	public String initParams(String...args) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(SDK_NAME + "初始化参数");
		stringBuilder.append(":");
		stringBuilder.append("appCode=");
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
		stringBuilder.append("feeCode=");
		stringBuilder.append(args[0]);
		stringBuilder.append(",money=");
		stringBuilder.append(args[1]);
		return stringBuilder.toString();
	}
}
