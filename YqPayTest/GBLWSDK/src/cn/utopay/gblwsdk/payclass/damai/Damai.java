package cn.utopay.gblwsdk.payclass.damai;


import android.app.Activity;

import com.android.dimtale.mtools.listener.MPayResultListener;
import com.android.dimtale.mtools.utils.MPay;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.utopay.gblwsdk.httpserver.ReportPaidThread;
import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.pay.Unipay;
import cn.utopay.gblwsdk.payclass.BasePay;

/**
 * 大麦支付
 */
public class Damai extends BasePay {

    public static final String SDK_NAME = "大麦支付";
    public static final String id = "14";
    private int SUCCESS_CODE = 1001;
    private boolean isPaySuccess;

    @Override
    public void init(final Activity activity) {
        super.init(activity);
        final Map<String, Object> initMap = getPayConfig().getInitMap();
        if (initMap != null) {
            String miMsa = initMap.get("msa").toString();
            MPay.getInstance().init(activity, miMsa, Unipay.channel);
        }
    }

    @Override
    public void pay(final Activity activity, final UniCallback uniCallback) {
        super.pay(activity, uniCallback);
        JSONObject payJson = getPayConfig().getPayParamJson();
        try {
            final String money = payJson.getString("money");
            // 商品标识
            final String gid = payJson.getString("gid");
            // cp自定义订单号
            String cpOid = Unipay.channel + "_" + System.currentTimeMillis();
            // CP自定义ext
            String ext = String.valueOf(System.currentTimeMillis());
            MPay.getInstance().pay(activity, gid, cpOid, ext, new MPayResultListener() {
                @Override
                public void callback(String s, int i, int code, String s1) {
                    if (code == SUCCESS_CODE) {
//                                if (isPaySuccess) {
//                                    return;
//                                }
//                                isPaySuccess = true;
                        String m = String.valueOf(Integer.parseInt(money) / 100);
                        ReportPaidThread.reportSuccess(activity,SDK_NAME, m, uniCallback);
                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            if (uniCallback != null) {
                uniCallback.payFailed(e);
            }
        }
    }
}
