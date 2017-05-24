package cn.utopay.gblwsdk.payclass.shangan;

import android.app.Activity;
import android.text.TextUtils;

import com.wc.k.Pau;

import org.json.JSONException;
import org.json.JSONObject;

import cn.utopay.gblwsdk.httpserver.ReportPaidThread;
import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.payclass.BasePay;

/**
 * 上岸支付
 * Created by Versace on 17/5/2.
 */
public class Shangan extends BasePay {

    public static final String SDK_CODE = "4e0a5cb8";
    public static final String SDK_NAME = "上岸";
    public static final String id = "16";
    private boolean isPaySuccess = false;

    @Override
    public void init(Activity activity) {
        super.init(activity);
    }

    @Override
    public void pay(Activity activity, UniCallback uniCallback) {
        super.pay(activity, uniCallback);
        JSONObject payJson = getPayConfig().getPayParamJson();
        if(payJson != null) {
            try {
                String money = payJson.getString("money");
                if(!TextUtils.isEmpty(payParams(new String[]{money}))){
                    print(activity,payParams(new String[]{money}));
                }
                new Pau().onPayStart(activity, false);
                String m = String.valueOf(Integer.parseInt(money)/100);
                ReportPaidThread.reportSuccess(activity,SDK_NAME, m, uniCallback);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String payParams(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SDK_NAME + "支付参数");
        stringBuilder.append(":");
        stringBuilder.append("money=");
        stringBuilder.append(args[0]);
        return stringBuilder.toString();
    }
}
