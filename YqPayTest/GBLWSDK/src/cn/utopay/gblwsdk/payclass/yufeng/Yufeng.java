package cn.utopay.gblwsdk.payclass.yufeng;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

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

    public static final String SDK_CODE = "73895cf0";
    public static final String SDK_NAME = "玉峰";
    public static final String id = "13";
    public static final int SUCCESS_CODE = 2000;
    private boolean isPaySuccess = false;
    private MjPaySDK mjPaySDK = null;

    @Override
    public void init(Activity activity) {
        //do nothing.
    }

    @Override
    public void init(final Activity activity, final UniCallback uniCallback) {
        super.init(activity, uniCallback);
        Map<String, Object> initMap = getPayConfig().getInitMap();
        if(initMap == null){
            print(activity,"初始化参数为空！");
            return;
        }
        String appId = initMap.get("appId").toString();
        final String money = initMap.get("money").toString();
        if(!TextUtils.isEmpty(initParams(new String[]{appId,money,Unipay.channel}))){
            print(activity,initParams(new String[]{appId,money,Unipay.channel}));
        }
        mjPaySDK = new MjPaySDK(activity, new BillingListener() {
            @Override
            public void onInitResult(int arg0) {

            }

            @Override
            public void onBillingResult(int code, Bundle b) {
                if (code == SUCCESS_CODE) {
                    String m = String.valueOf(Integer.parseInt(money) / 100);
                    ReportPaidThread.reportSuccess(activity,SDK_NAME, m, uniCallback);
                } else {
                    print(activity,SDK_NAME + "支付失败,code = " + code);
                }
            }
        }, appId, "", Unipay.channel);
    }

    @Override
    public void pay(Activity activity, UniCallback uniCallback) {
        super.pay(activity, uniCallback);
        init(activity,uniCallback);
        JSONObject payJson = getPayConfig().getPayParamJson();
        try {
            String payCode = payJson.getString("payCode");
            String money = payJson.getString("money");
            if(!TextUtils.isEmpty(payParams(new String[]{payCode,money,Unipay.channel + System.nanoTime()}))){
                print(activity,payParams(new String[]{payCode,money,Unipay.channel + System.nanoTime()}));
            }
            mjPaySDK.pay(Unipay.channel + System.nanoTime(), payCode, money);
        } catch (JSONException e) {
            e.printStackTrace();
            if (uniCallback != null) {
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
        stringBuilder.append(",money=");
        stringBuilder.append(args[1]);
        stringBuilder.append(",channel=");
        stringBuilder.append(args[2]);
        return stringBuilder.toString();
    }

    @Override
    public String payParams(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SDK_NAME + "支付参数");
        stringBuilder.append(":");
        stringBuilder.append("payCode=");
        stringBuilder.append(args[0]);
        stringBuilder.append(",money=");
        stringBuilder.append(args[1]);
        stringBuilder.append(",channel=");
        stringBuilder.append(args[2]);
        return stringBuilder.toString();
    }
}
