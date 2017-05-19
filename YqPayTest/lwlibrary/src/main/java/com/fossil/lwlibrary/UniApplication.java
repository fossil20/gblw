package com.fossil.lwlibrary;

import android.app.Application;

/**
 * Created by Kam Wong Division on 17/5/11.
 */
public class UniApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UniPay.getInstance().decryptSource(getApplicationContext());
    }
}
