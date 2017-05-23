package cn.utopay.gblwsdk.payclass.ePlusPay;

import android.app.Activity;
import android.text.TextUtils;

import com.eplus.internet.main.EPlus;
import com.eplus.internet.main.OnCallBackListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.utopay.gblwsdk.httpserver.ReportPaidThread;
import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.pay.Unipay;
import cn.utopay.gblwsdk.payclass.BasePay;

/**
 * Created by Versace on 17/4/25.
 */
public class EPlusPay extends BasePay{

    public static final String SDK_CODE = "4e2d81f3";
    public static final String SDK_NAME = "中至";
    private static final int SUCCESS_CODE = 200;
    public static final String id = "17";
    private static final String USER_ARGS = "strUserArgs";
    private boolean isPaySuccess = false;

    @Override
    public void init(final Activity activity) {
        super.init(activity);
        Map<String,Object> initMap = getPayConfig().getInitMap();
        String userId = initMap.get("userId").toString();
        String appId = initMap.get("appId").toString();
        String secretKey = initMap.get("key").toString();
        if(!TextUtils.isEmpty(initParams(new String[]{userId,appId,secretKey}))){
            print(activity,initParams(new String[]{userId,appId,secretKey}));
        }
        EPlus.getInstance().init(activity, userId, appId, secretKey, Unipay.channel, new OnCallBackListener() {
            @Override
            public void onResult(int status, String s) {
                if (status == 200) {
                    BasePay.print(activity,SDK_NAME + ":初始化成功,status = " + status);
                } else {
                    BasePay.print(activity,SDK_NAME + ":初始化失败,status = " + status);
                }
            }
        });
    }

    @Override
    public void pay(final Activity activity, final UniCallback uniCallback) {
        super.pay(activity,uniCallback);
        JSONObject payJson = getPayConfig().getPayParamJson();
        try {
            final String pointId = payJson.get("priciePointId").toString();
            final String money = payJson.get("money").toString();
            final String mm = String.valueOf(Integer.valueOf(money) / 100);
            if(!TextUtils.isEmpty(payParams(new String[]{pointId,money}))){
                print(activity,payParams(new String[]{pointId,money}));
            }
            EPlus.getInstance().doBuy(activity, money, pointId, USER_ARGS, false, new OnCallBackListener() {
                @Override
                public void onResult(int status, String s) {
                    if (status == SUCCESS_CODE) {
//                        if(isPaySuccess) {
//                            return;
//                        }
//                        isPaySuccess = true;
                        ReportPaidThread.reportSuccess(activity,SDK_NAME,mm,uniCallback);
                    } else {
                        BasePay.print(activity,SDK_NAME + ":计费失败,status = " + status);
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

    @Override
    public String initParams(String...args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SDK_NAME + "初始化参数");
        stringBuilder.append(":");
        stringBuilder.append("userId=");
        stringBuilder.append(args[0]);
        stringBuilder.append(",appId=");
        stringBuilder.append(args[1]);
        stringBuilder.append(",secretKey=");
        stringBuilder.append(args[2]);
        return stringBuilder.toString();
    }

    @Override
    public String payParams(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SDK_NAME + "支付参数");
        stringBuilder.append(":");
        stringBuilder.append("priciePointId=");
        stringBuilder.append(args[0]);
        stringBuilder.append(",money=");
        stringBuilder.append(args[1]);
        return stringBuilder.toString();
    }
}
