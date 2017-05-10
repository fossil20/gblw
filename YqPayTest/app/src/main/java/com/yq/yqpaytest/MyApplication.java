package com.yq.yqpaytest;

/**
 * Created by Versace on 17/5/2.
 */
public class MyApplication extends com.wyzf.pay.WyzfApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());
    }
}
