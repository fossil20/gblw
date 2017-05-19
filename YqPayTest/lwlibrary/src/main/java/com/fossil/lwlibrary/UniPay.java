package com.fossil.lwlibrary;

import android.app.Activity;
import android.content.Context;

import com.fossil.lwlibrary.method.Init;
import com.fossil.lwlibrary.method.Pay;
import com.fossil.lwlibrary.utils.EncryptThread;
import com.fossil.lwlibrary.utils.ThreadPool;

/**
 * Created by Kam Wong Division on 17/5/11.
 */
public class UniPay extends BaseManager {

    private static class SingletonHolder {
        private static final UniPay uniPay = new UniPay();
    }

    public static UniPay getInstance() {
        return SingletonHolder.uniPay;
    }

    public void init(final Activity activity) {
        execute(activity, Init.class);
    }

    public void decryptSource(final Context context) {
        ThreadPool.getInstance().executeTask(new EncryptThread(context));
    }

    public void pay(Activity activity) {
        execute(activity, Pay.class);
    }
}
