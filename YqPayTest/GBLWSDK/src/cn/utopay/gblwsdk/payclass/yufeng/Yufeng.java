package cn.utopay.gblwsdk.payclass.yufeng;

import android.app.Activity;
import android.os.Bundle;

import com.mj.jar.pay.BillingListener;
import com.mj.jar.pay.MjPaySDK;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.utopay.gblwsdk.httpserver.ReportPaidThread;
import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.pay.Unipay;
import cn.utopay.gblwsdk.payclass.BasePay;

/**
 * 玉峰支付
 */
public class Yufeng extends BasePay {

    public static final String SDK_NAME = "玉峰";
    public static final String id = "13";
    public static final int SUCCESS_CODE = 2000;
    private boolean isPaySuccess = false;
    private MjPaySDK mjPaySDK = null;

    @Override
    public void init(final Activity activity, final UniCallback uniCallback) {
        super.init(activity, uniCallback);
        Map<String, Object> initMap = getPayConfig().getInitMap();
        String appId = initMap.get("appId").toString();
        final String money = initMap.get("money").toString();
        mjPaySDK = new MjPaySDK(activity, new BillingListener() {
            @Override
            public void onInitResult(int arg0) {

            }

            @Override
            public void onBillingResult(int code, Bundle b) {
                if (code == SUCCESS_CODE) {
//					if(isPaySuccess) {
//						return;
//					}
//					isPaySuccess = true;
                    String m = String.valueOf(Integer.parseInt(money) / 100);
                    ReportPaidThread.reportSuccess(activity,SDK_NAME, m, uniCallback);
                } else {
                    print(activity,SDK_NAME + "支付失败");
                }
            }
        }, appId, "", Unipay.channel);
    }

    @Override
    public void pay(Activity activity, UniCallback uniCallback) {
        super.pay(activity, uniCallback);
        JSONObject payJson = getPayConfig().getPayParamJson();
        try {
            String payCode = payJson.getString("payCode");
            String money = payJson.getString("money");
            if (mjPaySDK != null) {
                mjPaySDK.pay(Unipay.channel + System.nanoTime(), payCode, money);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (uniCallback != null) {
                uniCallback.payFailed(e);
            }
        }
    }
}
