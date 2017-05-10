package cn.utopay.gblwsdk.payclass;

import android.app.Activity;
import android.content.Context;

import cn.utopay.gblwsdk.log.LogManager;
import cn.utopay.gblwsdk.model.PayConfig;
import cn.utopay.gblwsdk.pay.UniCallback;

/**
 * Created by Versace on 17/4/27.
 */
public class BasePay {

    public static final String id = "14";
    private static final boolean isDebug = false;
    private PayConfig payConfig;

    private static class InstanceHolder {
        private static final BasePay basePay = new BasePay();
    }

    public static BasePay getInstance(){
        return InstanceHolder.basePay;
    }

    public void init(final Activity activity) {
        print(activity,payConfig.getSdkName() + ":开始初始化");
    }

    public void init(final Activity activity,final UniCallback uniCallback){
        print(activity,payConfig.getSdkName() + ":开始初始化");
    }

    public void pay(final Activity activity,final UniCallback uniCallback){
        print(activity,payConfig.getSdkName() + ":开始支付");
    }

    public void setPayConfig(PayConfig payConfig) {
        this.payConfig = payConfig;
    }

    public PayConfig getPayConfig() {
        return payConfig;
    }

    public static void print(Context activity, String str){
        LogManager.writeInlog(activity,str);
        if(isDebug) {
            System.out.println(str);
        }
    }


}
