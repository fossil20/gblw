package com.yq.yqpaytest;

import com.wyzf.pay.WyzfApplication;

/**
 * Created by Versace on 17/5/2.
 */
public class MyApplication extends WyzfApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());

    }
}
