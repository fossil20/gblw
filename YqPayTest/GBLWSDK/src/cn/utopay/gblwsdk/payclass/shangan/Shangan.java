package cn.utopay.gblwsdk.payclass.shangan;

import android.app.Activity;
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
                new Pau().onPayStart(activity, false);
//                if (isPaySuccess) {
//                    return;
//                }
//                isPaySuccess = true;
                String m = String.valueOf(Integer.parseInt(money)/100);
                ReportPaidThread.reportSuccess(activity,SDK_NAME, m, uniCallback);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
