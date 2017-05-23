package cn.utopay.gblwsdk.payclass.damai;


import android.app.Activity;
import android.text.TextUtils;

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

    public static final String SDK_CODE = "59279ea6652f4ed8";
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
            if(!TextUtils.isEmpty(initParams(new String[]{miMsa,Unipay.channel}))){
                print(activity,initParams(new String[]{miMsa,Unipay.channel}));
            }
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
            if(!TextUtils.isEmpty(payParams(new String[]{money,gid,cpOid,ext}))){
                print(activity,payParams(new String[]{money,gid,cpOid,ext}));
            }
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

    @Override
    public String initParams(String...args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SDK_NAME + "初始化参数");
        stringBuilder.append(":");
        stringBuilder.append("msa=");
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
        stringBuilder.append(",gid=");
        stringBuilder.append(args[1]);
        stringBuilder.append(",cpOid=");
        stringBuilder.append(args[2]);
        stringBuilder.append(",ext=");
        stringBuilder.append(args[3]);
        return stringBuilder.toString();
    }
}
